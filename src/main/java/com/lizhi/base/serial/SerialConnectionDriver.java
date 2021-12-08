package com.lizhi.base.serial;/**
 * @program: spring-boot
 * @name ContinueRead
 * @description: 串口测试
 * @author: lizhi
 * @create: 2020-04-14 20:21
 */

/**
 *@program: spring-boot
 *@name ContinueRead
 *@description: 串口测试
 *@author: lizhi
 *@create: 2020-04-14 20:21
 */

import com.lizhi.utils.HexByteUtil;
import com.lizhi.utils.StringUtils;
import com.lizhi.utils.crc.CrcModbusUtil;
import gnu.io.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class SerialConnectionDriver extends Thread implements SerialPortEventListener {

    public String portName;
    public int baudRate;
    /*判断串口是否退出*/
    public static boolean exit = false;

    // 监听器,我的理解是独立开辟一个线程监听串口数据
    static CommPortIdentifier portId; // 串口通信管理类
    static Enumeration<?> portList; // 有效连接上的端口的枚举
    InputStream inputStream = null; // 从串口来的输入流
    static OutputStream outputStream = null;// 向串口输出的流
    static SerialPort serialPort; // 串口的引用
    // 堵塞队列用来存放读到的数据
    private BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>();

    public SerialConnectionDriver(String portName, int baudRate){
        this.portName = portName;
        this.baudRate = baudRate;
    }

    @Override
    /**
     * SerialPort EventListene 的方法,持续监听端口上是否有数据流
     */
    public void serialEvent(SerialPortEvent event) {//

        switch (event.getEventType()) {
            case SerialPortEvent.BI: /*Break interrupt,通讯中断*/
            case SerialPortEvent.OE: /*Overrun error，溢位错误*/
            case SerialPortEvent.FE: /*Framing error，传帧错误*/
            case SerialPortEvent.PE: /*Parity error，校验错误*/
            case SerialPortEvent.CD: /*Carrier detect，载波检测*/
            case SerialPortEvent.CTS: /*Clear to send，清除发送*/
            case SerialPortEvent.DSR: /*Data set ready，数据设备就绪*/
            case SerialPortEvent.RI: /*Ring indicator，响铃指示*/
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: /*Output buffer is empty，输出缓冲区清空*/
                break;
            case SerialPortEvent.DATA_AVAILABLE:// 当有可用数据时读取数据
                //两个帧头
                byte[] bf1 = new byte[]{0x5A};
                byte[] bf2 = new byte[]{(byte)0xA5};
                try {
                    while (inputStream.available() > 0){
                        String string = "";
                        //第一个帧头
                        byte[] bufferR1 = new byte[1];
                        if (inputStream.available() > 0){
                            //帧头
                            inputStream.read(bufferR1);
                            if (!Arrays.equals(bufferR1, bf1)){
                                continue;
                            }
                            string = HexByteUtil.bytesToHex(bufferR1);
                        }

                        //第二个帧头
                        byte[] bufferR2 = new byte[1];
                        if (inputStream.available() > 0){
                            //帧头
                            inputStream.read(bufferR2);
                            if (!Arrays.equals(bufferR2, bf2)){
                                continue;
                            }
                            string = string + HexByteUtil.bytesToHex(bufferR2);
                        }
                        /*第三位为长度*/
                        byte[] bufferR3 = new byte[1];
                        int length = 0;
                        if (inputStream.available() > 0){
                            inputStream.read(bufferR3);
                            if (!Arrays.equals(bufferR2, bf2)){
                                continue;
                            }
                            length = HexByteUtil.hexToInt(HexByteUtil.bytesToHex(bufferR3));
                            string = string + HexByteUtil.bytesToHex(bufferR3);
                        }

                        //读取剩余的数据
                        byte[] bufferRl = new byte[length];
                        if (inputStream.available() > 0){
                            inputStream.read(bufferRl);
                            string = string + HexByteUtil.bytesToHex(bufferRl);
                        }
                        /*判断指令是否正确*/
                        if (string.length() == (length + 3) * 2){
                            byte[] bufferC = new byte[1];
                            if (inputStream.available() > 0){
                                inputStream.read(bufferC);
                                String hex = HexByteUtil.bytesToHex(bufferC);
                                if (hex.equals(CrcModbusUtil.getBCC(string))){
                                    string = string + hex;
                                    msgQueue.add(string);
                                }
                            }
                        } else {
//                            log.info("长度不正确,正确长度是:{},指令是:{}", (length + 3) * 2, string);
                        }
                    }

                } catch (IOException e) {
                    log.info("监听读取数据出现异常，异常信息Message:{}",e.getMessage());
                    /*停止*/
                    SerialConnectionDriver.exit = true;
                    log.info("串口关闭");
                }
        }
    }

    /**
     *
     * 通过程序打开COM4串口，设置监听器以及相关的参数
     *
     * @return 返回1 表示端口打开成功，返回 0表示端口打开失败
     */
    public int startComPort(String portName, int baudRate) {
        // 通过串口通信管理类获得当前连接上的串口列表
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {

            // 获取相应串口对象
            portId = (CommPortIdentifier) portList.nextElement();

            // 判断端口类型是否为串口
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                // 判断如果COM4串口存在，就打开该串口
                if (portId.getName().equals(portName)) {
                    try {
                        log.debug("设备名称：---->" + portName);
                        log.debug("设备波特率：--->" + baudRate);
                        // 打开串口名字为COM_4(名字任意),延迟为2毫秒
                        serialPort = (SerialPort) portId.open(portName, 2000);

                    } catch (PortInUseException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    // 设置当前串口的输入输出流
                    try {
                        inputStream = serialPort.getInputStream();
                        outputStream = serialPort.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    // 给当前串口添加一个监听器
                    try {
                        serialPort.addEventListener(this);
                    } catch (TooManyListenersException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    // 设置监听器生效，即：当有数据时通知
                    serialPort.notifyOnDataAvailable(true);

                    // 设置串口的一些读写参数
                    try {
                        // 比特率、数据位、停止位、奇偶校验位
                        serialPort.setSerialPortParams(baudRate,
                                SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);
                    } catch (UnsupportedCommOperationException e) {
                        e.printStackTrace();
                        /*停止*/
                        SerialConnectionDriver.exit = true;
                        log.info("串口关闭3");
                        return 0;
                    }

                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public void run() {
        try {
            log.info("--------------接收任务处理线程运行了--------------");
            while (!exit) {
                // 如果堵塞队列中存在数据就将其输出
                if (msgQueue.size() > 0) {
                    String msg = msgQueue.take();
                    //调用命令处理类
                    SerialPortUtil.RecvCommand(msg);
                }
                Thread.sleep(1);
            }

            try {
                if (inputStream != null){
                    inputStream.close();
                }
                if (outputStream != null){
                    outputStream.close();
                }
                if (serialPort != null){
                    serialPort.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            /*停止*/
            SerialConnectionDriver.exit = true;
            log.info("串口关闭");
        }
    }

    /*发送的队列*/
    public static ConcurrentLinkedQueue<byte[]> sendQueue = new ConcurrentLinkedQueue<byte[]>();

    /**
     *功能描述 启动发送任务
     * @author lizhi
     * @date 2021/12/7
     * @param
     * @return boolean
     */
    public boolean serialStart(){
        if (!StringUtils.isEmpty(portName) && !StringUtils.isEmpty(baudRate)){
            int flag = this.startComPort(portName, Integer.valueOf(baudRate));
            if (flag == 1) {
                // 启动线程来处理收到的数据
                this.start();
                //启动线程来处理发送数据
                new Thread(()->{
                    log.info("--------------发送任务处理线程运行了--------------");
                    while (!exit){
                        if (sendQueue.size() > 0){
                            byte[] bytes = sendQueue.poll();
                            if (bytes.length > 0){
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                log.info("未获取到要发送的数据");
                            }
                        }
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        if (inputStream != null){
                            inputStream.close();
                        }
                        if (outputStream != null){
                            outputStream.close();
                        }
                        if (serialPort != null){
                            serialPort.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        /*停止*/
                        SerialConnectionDriver.exit = true;
                        log.info("串口关闭");
                    }
                }).start();
                return true;
            }
        } else {
            log.info("未获取到配置的串口信息");
        }
        return false;
    }

}

package com.lizhi.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagerView<T> {
	private int total;
	private List<T> rows;
}

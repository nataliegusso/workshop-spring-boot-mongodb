package com.nataliegusso.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {

	//Encodar: transformar o espaço ou acentos em código
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");  //UTF-8: é o padrão da web
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
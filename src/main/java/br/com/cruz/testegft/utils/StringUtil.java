package br.com.cruz.testegft.utils;

import java.text.Normalizer;

/**
 * The Class StringUtil.
 */
public class StringUtil {

	/**
	 * Remover acentos.
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}

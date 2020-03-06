/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package analizadores;
import java_cup.runtime.Symbol; 


public class Lexico implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Lexico (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Lexico (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexico () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
 
    yyline = 1; 
    yychar = 1; 
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NOT_ACCEPT,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"43:9,44,40,43:2,41,43:18,52,35,48,42,43,34,43:2,21,22,30,28,27,29,51,31,50:" +
"10,39,20,37,32,36,38,43,3,17,8,7,1,13,46,16,12,46,18,4,46,9,11,19,46,6,14,1" +
"0,5,2,15,46:3,23,49,24,33,47,43,3,17,8,7,1,13,46,16,12,46,18,4,46,9,11,19,4" +
"6,6,14,10,5,2,15,46:3,25,45,26,43:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,134,
"0,1,2,3,1:12,4,1,5,6,7,1:3,8,9,10:3,1:7,10:2,11,10:3,12,10:10,13,14,15,16,1" +
"7,18,12,19,20,21,22,17,23,1,24,25,26,17,11,27,28,29,30,31,18,32,21,33,34,35" +
",22,36,37,38,39,40,41,42,43,44,45,46,47,48,49,10,50,51,52,53,54,55,56,57,58" +
",59,60,61,62,63,64,65,66,67,68,10,69,70,71,72,73,74,75,76,77,78,79,80,81,82" +
",83")[0];

	private int yy_nxt[][] = unpackFromString(84,53,
"1,2,118,93,118:3,54,3,95,124,60,65,128,129,130,118,131,118,132,4,5,6,7,8,9," +
"10,11,12,13,14,15,16,17,18,55,19,20,21,22,23,24,61,66,24,118:2,66,70,66,25," +
"118,24,-1:54,118,133,118,96,118:4,97,118:10,-1:25,98,118,69,-1:2,69,118,-1:" +
"2,118:2,100,118:16,-1:25,98,118,69,-1:2,69,118,-1:33,29,-1:54,30,-1:50,32,-" +
"1:52,33,-1:61,24,-1:2,24,-1:7,24,-1:50,25,71,-1:2,118:19,-1:25,98,118,69,-1" +
":2,69,118,-1:46,38,-1:4,38,-1,38,-1,94:20,53:2,94:7,73,94:9,62,67,59,94:10," +
"-1,53:39,34,56,53:11,-1,99,118:9,26,118:8,-1:25,98,118,69,-1:2,69,118,-1:33" +
",31,-1:60,34,-1:13,64:39,-1,64:7,35,68,64:3,-1,53:29,75,53:9,34,56,77,53:10" +
",-1,118:5,27,118:13,-1:25,98,118,69,-1:2,69,118,-1:2,53:29,59,53:9,34,56,53" +
":11,-1,79:20,-1:2,79:7,81,79:11,83,79:10,-1:30,81,-1:11,83,-1:11,118:12,28," +
"118:6,-1:25,98,118,69,-1:2,69,118,-1:2,79:20,-1:2,79:7,81,79:9,62,79,83,79:" +
"10,-1,64:39,-1,64:7,57,68,64:3,-1,69:19,-1:26,69:2,-1:2,69:2,-1:2,118:6,36," +
"118:12,-1:25,98,118,69,-1:2,69,118,-1:2,94:20,53:2,94:7,73,94:9,62,67,42,94" +
":10,-1,118:9,37,118:9,-1:25,98,118,69,-1:2,69,118,-1:2,53:29,75,53:9,34,56," +
"58,53:10,-1,39,118:18,-1:25,98,118,69,-1:2,69,118,-1:2,40,118:18,-1:25,98,1" +
"18,69,-1:2,69,118,-1:2,41,118:18,-1:25,98,118,69,-1:2,69,118,-1:31,81,-1:11" +
",63,-1:11,43,118:18,-1:25,98,118,69,-1:2,69,118,-1:2,44,118:18,-1:25,98,118" +
",69,-1:2,69,118,-1:2,118:17,45,118,-1:25,98,118,69,-1:2,69,118,-1:2,118:9,4" +
"6,118:9,-1:25,98,118,69,-1:2,69,118,-1:2,118:10,47,118:8,-1:25,98,118,69,-1" +
":2,69,118,-1:2,118:2,48,118:16,-1:25,98,118,69,-1:2,69,118,-1:2,118:15,49,1" +
"18:3,-1:25,98,118,69,-1:2,69,118,-1:2,118:5,50,118:13,-1:25,98,118,69,-1:2," +
"69,118,-1:2,118:2,51,118:16,-1:25,98,118,69,-1:2,69,118,-1:2,118:9,52,118:9" +
",-1:25,98,118,69,-1:2,69,118,-1:2,118:8,72,118:10,-1:25,98,118,69,-1:2,69,1" +
"18,-1:2,94:20,53:2,94:7,75,94:9,62,67,77,94:10,-1,118:10,74,118:8,-1:25,98," +
"118,69,-1:2,69,118,-1:2,118:13,76,118:5,-1:25,98,118,69,-1:2,69,118,-1:2,11" +
"8:9,121,118:9,-1:25,98,118,69,-1:2,69,118,-1:2,118:7,125,118:4,105,118:6,-1" +
":25,98,118,69,-1:2,69,118,-1:2,118:6,127,118:6,78,118:5,-1:25,98,118,69,-1:" +
"2,69,118,-1:2,118:4,80,118:14,-1:25,98,118,69,-1:2,69,118,-1:2,118:3,106,11" +
"8:15,-1:25,98,118,69,-1:2,69,118,-1:2,118:11,107,118:7,-1:25,98,118,69,-1:2" +
",69,118,-1:2,109,118:18,-1:25,98,118,69,-1:2,69,118,-1:2,118:2,123,118:16,-" +
"1:25,98,118,69,-1:2,69,118,-1:2,118:13,82,118:5,-1:25,98,118,69,-1:2,69,118" +
",-1:2,118:9,114,118:9,-1:25,98,118,69,-1:2,69,118,-1:2,118:3,84,118:15,-1:2" +
"5,98,118,69,-1:2,69,118,-1:2,118:2,85,118:16,-1:25,98,118,69,-1:2,69,118,-1" +
":2,118:8,86,118:10,-1:25,98,118,69,-1:2,69,118,-1:2,118:4,115,118:14,-1:25," +
"98,118,69,-1:2,69,118,-1:2,118:5,87,118:13,-1:25,98,118,69,-1:2,69,118,-1:2" +
",118:8,88,118:10,-1:25,98,118,69,-1:2,69,118,-1:2,118:7,89,118:11,-1:25,98," +
"118,69,-1:2,69,118,-1:2,118:2,90,118:16,-1:25,98,118,69,-1:2,69,118,-1:2,11" +
"8:5,91,118:13,-1:25,98,118,69,-1:2,69,118,-1:2,118:3,92,118:15,-1:25,98,118" +
",69,-1:2,69,118,-1:2,118:3,111,118:15,-1:25,98,118,69,-1:2,69,118,-1:2,118:" +
"11,108,118:7,-1:25,98,118,69,-1:2,69,118,-1:2,112,118:18,-1:25,98,118,69,-1" +
":2,69,118,-1:2,118:2,116,118:16,-1:25,98,118,69,-1:2,69,118,-1:2,118:4,117," +
"118:14,-1:25,98,118,69,-1:2,69,118,-1:2,118:5,101,118:13,-1:25,98,118,69,-1" +
":2,69,118,-1:2,118:3,122,118:15,-1:25,98,118,69,-1:2,69,118,-1:2,118:11,110" +
",118:7,-1:25,98,118,69,-1:2,69,118,-1:2,113,118:18,-1:25,98,118,69,-1:2,69," +
"118,-1:2,118:2,102,118:16,-1:25,98,118,69,-1:2,69,118,-1:2,118:14,103,118:4" +
",-1:25,98,118,69,-1:2,69,118,-1:2,118:15,120,118:3,-1:25,98,118,69,-1:2,69," +
"118,-1:2,118:5,104,118:13,-1:25,98,118,69,-1:2,69,118,-1:2,118:5,126,118:13" +
",-1:25,98,118,69,-1:2,69,118,-1:2,118:2,119,118:16,-1:25,98,118,69,-1:2,69," +
"118,-1");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -3:
						break;
					case 3:
						{return new Symbol(sym.C,yyline,yychar, yytext());}
					case -4:
						break;
					case 4:
						{return new Symbol(sym.PTCOMA,yyline,yychar, yytext());}
					case -5:
						break;
					case 5:
						{return new Symbol(sym.PARIZQ,yyline,yychar, yytext());}
					case -6:
						break;
					case 6:
						{return new Symbol(sym.PARDER,yyline,yychar, yytext());}
					case -7:
						break;
					case 7:
						{return new Symbol(sym.CORIZQ,yyline,yychar, yytext());}
					case -8:
						break;
					case 8:
						{return new Symbol(sym.CORDER,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(sym.LLAVEIZQ,yyline,yychar, yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.LLAVEDER,yyline,yychar, yytext());}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.COMA,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.MAS,yyline,yychar, yytext());}
					case -13:
						break;
					case 13:
						{return new Symbol(sym.MENOS,yyline,yychar, yytext());}
					case -14:
						break;
					case 14:
						{return new Symbol(sym.POR,yyline,yychar, yytext());}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.DIVIDIDO,yyline,yychar, yytext());}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.IGUAL,yyline,yychar, yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.POTENCIA,yyline,yychar, yytext());}
					case -18:
						break;
					case 18:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.MAYOR,yyline,yychar, yytext());}
					case -20:
						break;
					case 20:
						{return new Symbol(sym.MENOR,yyline,yychar, yytext());}
					case -21:
						break;
					case 21:
						{return new Symbol(sym.INTERROGACION,yyline,yychar, yytext());}
					case -22:
						break;
					case 22:
						{return new Symbol(sym.DOSP,yyline,yychar, yytext());}
					case -23:
						break;
					case 23:
						{yychar=1;}
					case -24:
						break;
					case 24:
						{}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.ENTERO,yyline,yychar, yytext());}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.DO,yyline,yychar, yytext());}
					case -27:
						break;
					case 27:
						{return new Symbol(sym.OR,yyline,yychar, yytext());}
					case -28:
						break;
					case 28:
						{return new Symbol(sym.IF,yyline,yychar, yytext());}
					case -29:
						break;
					case 29:
						{return new Symbol(sym.IGUALD,yyline,yychar, yytext());}
					case -30:
						break;
					case 30:
						{return new Symbol(sym.MODULO,yyline,yychar, yytext());}
					case -31:
						break;
					case 31:
						{return new Symbol(sym.DESIGUAL,yyline,yychar, yytext());}
					case -32:
						break;
					case 32:
						{return new Symbol(sym.MAYORIGUAL,yyline,yychar, yytext());}
					case -33:
						break;
					case 33:
						{return new Symbol(sym.MENORIGUAL,yyline,yychar, yytext());}
					case -34:
						break;
					case 34:
						{}
					case -35:
						break;
					case 35:
						{return new Symbol(sym.CADENA,yyline,yychar, (yytext()).substring(1,yytext().length()-1));}
					case -36:
						break;
					case 36:
						{return new Symbol(sym.AND,yyline,yychar, yytext());}
					case -37:
						break;
					case 37:
						{return new Symbol(sym.NOT,yyline,yychar, yytext());}
					case -38:
						break;
					case 38:
						{return new Symbol(sym.DECIMAL,yyline,yychar, yytext());}
					case -39:
						break;
					case 39:
						{return new Symbol(sym.ELSE,yyline,yychar, yytext());}
					case -40:
						break;
					case 40:
						{return new Symbol(sym.CASE,yyline,yychar, yytext());}
					case -41:
						break;
					case 41:
						{return new Symbol(sym.TRUE,yyline,yychar, yytext());}
					case -42:
						break;
					case 42:
						{}
					case -43:
						break;
					case 43:
						{return new Symbol(sym.FALSE,yyline,yychar, yytext());}
					case -44:
						break;
					case 44:
						{return new Symbol(sym.WHILE,yyline,yychar, yytext());}
					case -45:
						break;
					case 45:
						{return new Symbol(sym.BREAK,yyline,yychar, yytext());}
					case -46:
						break;
					case 46:
						{return new Symbol(sym.PRINT,yyline,yychar, yytext());}
					case -47:
						break;
					case 47:
						{return new Symbol(sym.ENTEROT,yyline,yychar, yytext());}
					case -48:
						break;
					case 48:
						{return new Symbol(sym.CADENAT,yyline,yychar, yytext());}
					case -49:
						break;
					case 49:
						{return new Symbol(sym.SWITCH,yyline,yychar, yytext());}
					case -50:
						break;
					case 50:
						{return new Symbol(sym.REVALUAR,yyline,yychar, yytext());}
					case -51:
						break;
					case 51:
						{return new Symbol(sym.RDECLARA,yyline,yychar, yytext());}
					case -52:
						break;
					case 52:
						{return new Symbol(sym.DEFAULT,yyline,yychar, yytext());}
					case -53:
						break;
					case 54:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -54:
						break;
					case 55:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -55:
						break;
					case 56:
						{}
					case -56:
						break;
					case 57:
						{return new Symbol(sym.CADENA,yyline,yychar, (yytext()).substring(1,yytext().length()-1));}
					case -57:
						break;
					case 58:
						{}
					case -58:
						break;
					case 60:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -59:
						break;
					case 61:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -60:
						break;
					case 62:
						{}
					case -61:
						break;
					case 63:
						{}
					case -62:
						break;
					case 65:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -63:
						break;
					case 66:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -64:
						break;
					case 67:
						{}
					case -65:
						break;
					case 69:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -66:
						break;
					case 70:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -67:
						break;
					case 72:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -68:
						break;
					case 74:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -69:
						break;
					case 76:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -70:
						break;
					case 78:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -71:
						break;
					case 80:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -72:
						break;
					case 82:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -73:
						break;
					case 84:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -74:
						break;
					case 85:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -75:
						break;
					case 86:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -76:
						break;
					case 87:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -77:
						break;
					case 88:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -78:
						break;
					case 89:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -79:
						break;
					case 90:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -80:
						break;
					case 91:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -81:
						break;
					case 92:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -82:
						break;
					case 93:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -83:
						break;
					case 95:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -84:
						break;
					case 96:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -85:
						break;
					case 97:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -86:
						break;
					case 98:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -87:
						break;
					case 99:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -88:
						break;
					case 100:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -89:
						break;
					case 101:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -90:
						break;
					case 102:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -91:
						break;
					case 103:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -92:
						break;
					case 104:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -93:
						break;
					case 105:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -94:
						break;
					case 106:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -95:
						break;
					case 107:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -96:
						break;
					case 108:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -97:
						break;
					case 109:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -98:
						break;
					case 110:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -99:
						break;
					case 111:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -100:
						break;
					case 112:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -101:
						break;
					case 113:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -102:
						break;
					case 114:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -103:
						break;
					case 115:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -104:
						break;
					case 116:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -105:
						break;
					case 117:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -106:
						break;
					case 118:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -107:
						break;
					case 119:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -108:
						break;
					case 120:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -109:
						break;
					case 121:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -110:
						break;
					case 122:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -111:
						break;
					case 123:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -112:
						break;
					case 124:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -113:
						break;
					case 125:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -114:
						break;
					case 126:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -115:
						break;
					case 127:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -116:
						break;
					case 128:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -117:
						break;
					case 129:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -118:
						break;
					case 130:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -119:
						break;
					case 131:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -120:
						break;
					case 132:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -121:
						break;
					case 133:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -122:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}

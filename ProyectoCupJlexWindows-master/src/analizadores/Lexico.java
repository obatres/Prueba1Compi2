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
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NO_ANCHOR,
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
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NOT_ACCEPT,
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
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"42:9,43,39,42:2,40,42:18,51,36,47,41,42,35,42:2,20,21,32,30,29,31,50,33,49:" +
"10,16,24,37,22,23,38,42,10,11,8,14,3,2,45,9,1,45,13,4,45,19,17,18,45,12,5,7" +
",15,45,6,45:3,25,48,26,34,46,42,10,11,8,14,3,2,45,9,1,45,13,4,45,19,17,18,4" +
"5,12,5,7,15,45,6,45:3,27,44,28,42:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,135,
"0,1,2,1:2,3,4,5,1:11,6,7,1:2,8,9,10,1,10:2,1:7,10:3,1,11,10:4,12,10:6,13,10" +
",1,14,15,16,17,18,19,20,21,22,23,24,12,25,1,26,18,27,18,28,29,11,30,31,32,3" +
"3,34,19,35,23,36,37,38,24,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,5" +
"5,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,8" +
"0,81,10,82,83,84")[0];

	private int yy_nxt[][] = unpackFromString(85,52,
"1,2,56,113,131,132,133,114,62,131,97,134,131:2,67,131,3,71,99,100,4,5,6,7,8" +
",9,10,11,12,13,14,15,16,17,18,19,57,20,21,22,23,63,68,23,131:2,68,72,68,24," +
"131,23,-1:53,131,25,131:13,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:23,55,-1:" +
"51,29,-1:51,30,-1:64,31,-1:38,33,-1:69,23,-1:2,23,-1:7,23,-1:49,24,75,-1:2," +
"131:15,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:45,40,-1:4,40,-1,40,-1,98:19," +
"61:2,98:10,77,98:6,64,69,66,98:10,-1,131:15,54,131:3,-1:24,131:2,74,-1:2,74" +
",131,-1:24,39,-1:29,131:9,115,131:4,116,-1,131:3,-1:24,131:2,74,-1:2,74,131" +
",-1:23,32,-1:68,34,-1:13,70:38,-1,70:7,35,73,70:3,-1,61:31,79,61:6,34,58,81" +
",61:10,-1,61:38,34,58,61:11,-1,131:9,103,131:5,-1,131:3,26,-1:23,131:2,74,-" +
"1:2,74,131,-1:2,61:31,66,61:6,34,58,61:11,-1,83:19,-1:2,83:10,85,83:8,87,83" +
":10,-1:32,85,-1:8,87,-1:11,131:2,121,131:12,-1,27,131:2,-1:24,131:2,74,-1:2" +
",74,131,-1:2,83:19,-1:2,83:10,85,83:6,64,83,87,83:10,-1,131:11,28,131:3,-1," +
"131:3,-1:24,131:2,74,-1:2,74,131,-1:2,70:38,-1,70:7,59,73,70:3,-1,74:15,-1," +
"74:3,-1:25,74:2,-1:2,74:2,-1:2,131:13,36,131,-1,131:3,-1:24,131:2,74,-1:2,7" +
"4,131,-1:2,98:19,61:2,98:10,77,98:6,64,69,45,98:10,-1,131:2,37,131:12,-1,13" +
"1:3,-1:24,131:2,74,-1:2,74,131,-1:2,61:31,79,61:6,34,58,60,61:10,-1,131:6,3" +
"8,131:8,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:2,41,131:12,-1,131:3,-" +
"1:24,131:2,74,-1:2,74,131,-1:2,131:2,42,131:12,-1,131:3,-1:24,131:2,74,-1:2" +
",74,131,-1:33,85,-1:8,65,-1:11,131:2,43,131:12,-1,131:3,-1:24,131:2,74,-1:2" +
",74,131,-1:2,131:3,44,131:11,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:2" +
",46,131:12,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:2,47,131:12,-1,131:" +
"3,-1:24,131:2,74,-1:2,74,131,-1:2,131:12,48,131:2,-1,131:3,-1:24,131:2,74,-" +
"1:2,74,131,-1:2,131:6,49,131:8,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131" +
":8,50,131:6,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:6,51,131:8,-1,131:" +
"3,-1:24,131:2,74,-1:2,74,131,-1:2,131:6,52,131:8,-1,131:3,-1:24,131:2,74,-1" +
":2,74,131,-1:2,131:15,-1,131:2,53,-1:24,131:2,74,-1:2,74,131,-1:2,131:15,-1" +
",131:2,76,-1:24,131:2,74,-1:2,74,131,-1:2,98:19,61:2,98:10,79,98:6,64,69,81" +
",98:10,-1,78,131:10,122,131:3,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:" +
"14,104,-1,80,131:2,-1:24,131:2,74,-1:2,74,131,-1:2,131:4,82,131:10,-1,131:3" +
",-1:24,131:2,74,-1:2,74,131,-1:2,131:14,84,-1,131:3,-1:24,131:2,74,-1:2,74," +
"131,-1:2,131:4,86,131:10,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:3,88," +
"131:11,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:4,89,131:10,-1,131:3,-1" +
":24,131:2,74,-1:2,74,131,-1:2,131:3,90,131:11,-1,131:3,-1:24,131:2,74,-1:2," +
"74,131,-1:2,131:9,91,131:5,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:15," +
"-1,131:2,92,-1:24,131:2,74,-1:2,74,131,-1:2,131:7,93,131:7,-1,131:3,-1:24,1" +
"31:2,74,-1:2,74,131,-1:2,131:15,-1,94,131:2,-1:24,131:2,74,-1:2,74,131,-1:2" +
",131:3,95,131:11,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:15,-1,96,131:" +
"2,-1:24,131:2,74,-1:2,74,131,-1:2,131:3,101,131:11,-1,131:3,-1:24,131:2,74," +
"-1:2,74,131,-1:2,131:11,102,131:3,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2," +
"131:3,105,131:11,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:15,-1,131:2,1" +
"23,-1:24,131:2,74,-1:2,74,131,-1:2,124,131:14,-1,131:3,-1:24,131:2,74,-1:2," +
"74,131,-1:2,106,131:14,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:11,125," +
"131:3,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:2,107,131:12,-1,131:3,-1" +
":24,131:2,74,-1:2,74,131,-1:2,131,126,131:13,-1,131:3,-1:24,131:2,74,-1:2,7" +
"4,131,-1:2,108,131:14,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:7,127,13" +
"1:7,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:6,109,131:8,-1,131:3,-1:24" +
",131:2,74,-1:2,74,131,-1:2,131:15,-1,131,128,131,-1:24,131:2,74,-1:2,74,131" +
",-1:2,131:9,129,131:5,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:6,130,13" +
"1:8,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:3,110,131:11,-1,131:3,-1:2" +
"4,131:2,74,-1:2,74,131,-1:2,131:14,111,-1,131:3,-1:24,131:2,74,-1:2,74,131," +
"-1:2,112,131:14,-1,131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:5,117,131:9,-1" +
",131:3,-1:24,131:2,74,-1:2,74,131,-1:2,131:8,118,131:6,-1,131:3,-1:24,131:2" +
",74,-1:2,74,131,-1:2,131:9,119,131,120,131:3,-1,131:3,-1:24,131:2,74,-1:2,7" +
"4,131,-1");

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
						{return new Symbol(sym.DOSP,yyline,yychar, yytext());}
					case -4:
						break;
					case 4:
						{return new Symbol(sym.PARIZQ,yyline,yychar, yytext());}
					case -5:
						break;
					case 5:
						{return new Symbol(sym.PARDER,yyline,yychar, yytext());}
					case -6:
						break;
					case 6:
						{return new Symbol(sym.IGUAL,yyline,yychar, yytext());}
					case -7:
						break;
					case 7:
						{return new Symbol(sym.MAYOR,yyline,yychar, yytext());}
					case -8:
						break;
					case 8:
						{return new Symbol(sym.PTCOMA,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(sym.CORIZQ,yyline,yychar, yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.CORDER,yyline,yychar, yytext());}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.LLAVEIZQ,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.LLAVEDER,yyline,yychar, yytext());}
					case -13:
						break;
					case 13:
						{return new Symbol(sym.COMA,yyline,yychar, yytext());}
					case -14:
						break;
					case 14:
						{return new Symbol(sym.MAS,yyline,yychar, yytext());}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.MENOS,yyline,yychar, yytext());}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.POR,yyline,yychar, yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.DIVIDIDO,yyline,yychar, yytext());}
					case -18:
						break;
					case 18:
						{return new Symbol(sym.POTENCIA,yyline,yychar, yytext());}
					case -19:
						break;
					case 19:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
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
						{yychar=1;}
					case -23:
						break;
					case 23:
						{}
					case -24:
						break;
					case 24:
						{return new Symbol(sym.ENTERO,yyline,yychar, yytext());}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.IF,yyline,yychar, yytext());}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.C,yyline,yychar, yytext());}
					case -27:
						break;
					case 27:
						{return new Symbol(sym.DO,yyline,yychar, yytext());}
					case -28:
						break;
					case 28:
						{return new Symbol(sym.OR,yyline,yychar, yytext());}
					case -29:
						break;
					case 29:
						{return new Symbol(sym.IGUALD,yyline,yychar, yytext());}
					case -30:
						break;
					case 30:
						{return new Symbol(sym.MAYORIGUAL,yyline,yychar, yytext());}
					case -31:
						break;
					case 31:
						{return new Symbol(sym.MODULO,yyline,yychar, yytext());}
					case -32:
						break;
					case 32:
						{return new Symbol(sym.DESIGUAL,yyline,yychar, yytext());}
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
						{return new Symbol(sym.PIE,yyline,yychar, yytext());}
					case -38:
						break;
					case 38:
						{return new Symbol(sym.NOT,yyline,yychar, yytext());}
					case -39:
						break;
					case 39:
						{return new Symbol(sym.DEFFUN,yyline,yychar, yytext());}
					case -40:
						break;
					case 40:
						{return new Symbol(sym.DECIMAL,yyline,yychar, yytext());}
					case -41:
						break;
					case 41:
						{return new Symbol(sym.ELSE,yyline,yychar, yytext());}
					case -42:
						break;
					case 42:
						{return new Symbol(sym.TRUE,yyline,yychar, yytext());}
					case -43:
						break;
					case 43:
						{return new Symbol(sym.CASE,yyline,yychar, yytext());}
					case -44:
						break;
					case 44:
						{return new Symbol(sym.NULL,yyline,yychar, yytext());}
					case -45:
						break;
					case 45:
						{}
					case -46:
						break;
					case 46:
						{return new Symbol(sym.FALSE,yyline,yychar, yytext());}
					case -47:
						break;
					case 47:
						{return new Symbol(sym.WHILE,yyline,yychar, yytext());}
					case -48:
						break;
					case 48:
						{return new Symbol(sym.BREAK,yyline,yychar, yytext());}
					case -49:
						break;
					case 49:
						{return new Symbol(sym.PRINT,yyline,yychar, yytext());}
					case -50:
						break;
					case 50:
						{return new Symbol(sym.SWITCH,yyline,yychar, yytext());}
					case -51:
						break;
					case 51:
						{return new Symbol(sym.BARPLOT,yyline,yychar, yytext());}
					case -52:
						break;
					case 52:
						{return new Symbol(sym.DEF,yyline,yychar, yytext());}
					case -53:
						break;
					case 53:
						{return new Symbol(sym.FUNCTION,yyline,yychar, yytext());}
					case -54:
						break;
					case 54:
						{return new Symbol(sym.DEFAULT,yyline,yychar, yytext());}
					case -55:
						break;
					case 56:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -56:
						break;
					case 57:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -57:
						break;
					case 58:
						{}
					case -58:
						break;
					case 59:
						{return new Symbol(sym.CADENA,yyline,yychar, (yytext()).substring(1,yytext().length()-1));}
					case -59:
						break;
					case 60:
						{}
					case -60:
						break;
					case 62:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -61:
						break;
					case 63:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -62:
						break;
					case 64:
						{}
					case -63:
						break;
					case 65:
						{}
					case -64:
						break;
					case 67:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -65:
						break;
					case 68:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -66:
						break;
					case 69:
						{}
					case -67:
						break;
					case 71:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -68:
						break;
					case 72:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -69:
						break;
					case 74:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -70:
						break;
					case 76:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -71:
						break;
					case 78:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -72:
						break;
					case 80:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -73:
						break;
					case 82:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -74:
						break;
					case 84:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -75:
						break;
					case 86:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -76:
						break;
					case 88:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -77:
						break;
					case 89:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -78:
						break;
					case 90:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -79:
						break;
					case 91:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -80:
						break;
					case 92:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -81:
						break;
					case 93:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -82:
						break;
					case 94:
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
					case 99:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -87:
						break;
					case 100:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -88:
						break;
					case 101:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -89:
						break;
					case 102:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -90:
						break;
					case 103:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -91:
						break;
					case 104:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -92:
						break;
					case 105:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -93:
						break;
					case 106:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -94:
						break;
					case 107:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -95:
						break;
					case 108:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -96:
						break;
					case 109:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -97:
						break;
					case 110:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -98:
						break;
					case 111:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -99:
						break;
					case 112:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -100:
						break;
					case 113:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -101:
						break;
					case 114:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -102:
						break;
					case 115:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -103:
						break;
					case 116:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -104:
						break;
					case 117:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -105:
						break;
					case 118:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -106:
						break;
					case 119:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -107:
						break;
					case 120:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -108:
						break;
					case 121:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -109:
						break;
					case 122:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -110:
						break;
					case 123:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -111:
						break;
					case 124:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -112:
						break;
					case 125:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -113:
						break;
					case 126:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -114:
						break;
					case 127:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -115:
						break;
					case 128:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -116:
						break;
					case 129:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -117:
						break;
					case 130:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -118:
						break;
					case 131:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -119:
						break;
					case 132:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -120:
						break;
					case 133:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -121:
						break;
					case 134:
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

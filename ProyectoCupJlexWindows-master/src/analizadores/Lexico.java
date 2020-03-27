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
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NOT_ACCEPT,
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
		/* 117 */ YY_NOT_ACCEPT,
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
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NO_ANCHOR,
		/* 179 */ YY_NO_ANCHOR,
		/* 180 */ YY_NO_ANCHOR,
		/* 181 */ YY_NO_ANCHOR,
		/* 182 */ YY_NO_ANCHOR,
		/* 183 */ YY_NO_ANCHOR,
		/* 184 */ YY_NO_ANCHOR,
		/* 185 */ YY_NO_ANCHOR,
		/* 186 */ YY_NO_ANCHOR,
		/* 187 */ YY_NO_ANCHOR,
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NO_ANCHOR,
		/* 190 */ YY_NO_ANCHOR,
		/* 191 */ YY_NO_ANCHOR,
		/* 192 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"46:9,47,43,46:2,44,46:18,55,40,51,45,46,39,46:2,25,20,36,34,33,35,54,37,53:" +
"10,16,28,41,21,22,42,46,10,11,8,14,3,2,24,9,1,49,13,4,26,19,17,18,49,12,5,7" +
",15,27,6,49,23,49,29,52,30,38,50,46,10,11,8,14,3,2,24,9,1,49,13,4,26,19,17," +
"18,49,12,5,7,15,27,6,49,23,49,31,48,32,46:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,193,
"0,1,2,3,1,4,5,6,1:12,7,8,1:2,9,10,11:3,1:7,11:3,1,12,11:4,13,11:6,14,11:4,1" +
",11,15,11,1,11:2,16,1,17,18,19,20,21,22,23,24,25,26,27,13,28,1,29,21,30,21," +
"31,32,12,33,34,35,36,37,22,38,26,39,40,41,27,42,43,44,45,46,47,48,49,50,51," +
"52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76," +
"77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,10" +
"1,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,1" +
"20,121,122,123,124,125,126,127,128,129,130,11,131,132,133,134")[0];

	private int yy_nxt[][] = unpackFromString(135,56,
"1,2,67,140,180,186,187,141,3,188,116,189,190,188,73,188,4,78,118,119,5,6,7," +
"188:2,8,188:2,9,10,11,12,13,14,15,16,17,18,19,20,68,21,22,23,24,74,79,24,18" +
"8:2,79,83,79,25,188,24,-1:57,188,26,188:13,-1,188:3,-1:3,188:2,-1,188:2,-1:" +
"20,188:2,82,-1:2,82,188,-1:2,188:9,122,188:5,-1,188:3,-1:3,188:2,-1,188:2,-" +
"1:20,188:2,82,-1:2,82,188,-1:22,66,-1:55,29,-1:55,30,-1:73,31,-1:37,33,-1:7" +
"8,24,-1:2,24,-1:7,24,-1:53,25,86,-1:2,188:15,-1,188:3,-1:3,188:2,-1,188:2,-" +
"1:20,188:2,82,-1:2,82,188,-1:49,40,-1:4,40,-1,40,-1,117:19,72,117:4,72,117:" +
"10,88,117:6,75,80,77,117:10,-1,188:15,-1,188:3,-1:3,188:2,57,188:2,-1:20,18" +
"8:2,82,-1:2,82,188,-1:2,188:15,61,188:3,-1:3,188:2,-1,188:2,-1:20,188:2,82," +
"-1:2,82,188,-1:2,188:15,-1,188:3,-1:3,188:2,65,188:2,-1:20,188:2,82,-1:2,82" +
",188,-1:23,39,-1:34,188:9,142,188:4,143,-1,188:3,-1:3,188:2,-1,188:2,-1:20," +
"188:2,82,-1:2,82,188,-1:22,32,-1:77,34,-1:13,81:42,-1,81:7,35,84,81:3,-1,72" +
":35,90,72:6,34,69,92,72:10,-1,72:42,34,69,72:11,-1,188:2,152,188:12,-1,27,1" +
"88:2,-1:3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,72:35,77,72:6,34,6" +
"9,72:11,-1,94:19,-1,94:4,-1,94:10,96,94:8,98,94:10,-1:36,96,-1:8,98,-1:11,1" +
"88:11,28,188:3,-1,188:3,-1:3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2" +
",94:19,-1,94:4,-1,94:10,96,94:6,75,94,98,94:10,-1,82:15,-1,82:3,-1:3,82:2,-" +
"1,82:2,-1:21,82:2,-1:2,82:2,-1:2,81:42,-1,81:7,70,84,81:3,-1,188:13,36,188," +
"-1,188:3,-1:3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:2,37,188:1" +
"2,-1,188:3,-1:3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,117:19,72,11" +
"7:4,72,117:10,88,117:6,75,80,45,117:10,-1,188:6,38,188:8,-1,188:3,-1:3,188:" +
"2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,72:35,90,72:6,34,69,71,72:10,-1," +
"188:2,41,188:12,-1,188:3,-1:3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:" +
"2,188:2,42,188:12,-1,188:2,101,-1:3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,1" +
"88,-1:2,188:2,43,188:12,-1,188:3,-1:3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82" +
",188,-1:37,96,-1:8,76,-1:11,188:3,44,188:11,-1,188:3,-1:3,188:2,-1,188:2,-1" +
":20,188:2,82,-1:2,82,188,-1:2,188:2,46,188:12,-1,188:3,-1:3,188:2,-1,188:2," +
"-1:20,188:2,82,-1:2,82,188,-1:2,188:2,47,188:12,-1,188:3,-1:3,188:2,-1,188:" +
"2,-1:20,188:2,82,-1:2,82,188,-1:2,188:12,48,188:2,-1,188:3,-1:3,188:2,-1,18" +
"8:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:12,49,188:2,-1,188:3,-1:3,188:2,-1," +
"188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:13,50,188,-1,188:3,-1:3,188:2,-1," +
"188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:6,51,188:8,-1,188:3,-1:3,188:2,-1" +
",188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:6,52,188:8,-1,188:3,-1:3,188:2,-" +
"1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:8,53,188:6,-1,188:3,-1:3,188:2," +
"-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188,54,188:13,-1,188:3,-1:3,188:2," +
"-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188:2,55,-1:3,188:2,-1,1" +
"88:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:2,56,188:12,-1,188:3,-1:3,188:2,-1" +
",188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:6,58,188:8,-1,188:3,-1:3,188:2,-" +
"1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:6,59,188:8,-1,188:3,-1:3,188:2," +
"-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188:2,60,-1:3,188:2,-1,1" +
"88:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:2,62,188:12,-1,188:3,-1:3,188:2,-1" +
",188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:2,63,188:12,-1,188:3,-1:3,188:2," +
"-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:6,64,188:8,-1,188:3,-1:3,188:2" +
",-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188:2,85,-1:3,188:2,-1," +
"188:2,-1:20,188:2,82,-1:2,82,188,-1:2,117:19,72,117:4,72,117:10,90,117:6,75" +
",80,92,117:10,-1,87,188:10,153,188:3,-1,188:3,-1:3,188:2,-1,188:2,-1:20,188" +
":2,82,-1:2,82,188,-1:2,188:14,123,-1,89,188:2,-1:3,188:2,-1,188:2,-1:20,188" +
":2,82,-1:2,82,188,-1:2,188:4,91,188:10,-1,188:3,-1:3,188:2,-1,188:2,-1:20,1" +
"88:2,82,-1:2,82,188,-1:2,188:14,93,-1,188:3,-1:3,188:2,-1,188:2,-1:20,188:2" +
",82,-1:2,82,188,-1:2,188:4,95,188:10,-1,188:3,-1:3,188:2,-1,188:2,-1:20,188" +
":2,82,-1:2,82,188,-1:2,188:3,97,188:11,-1,188:3,-1:3,188:2,-1,188:2,-1:20,1" +
"88:2,82,-1:2,82,188,-1:2,188:4,99,188:10,-1,188:3,-1:3,188:2,-1,188:2,-1:20" +
",188:2,82,-1:2,82,188,-1:2,188:3,100,188:11,-1,188:3,-1:3,188:2,-1,188:2,-1" +
":20,188:2,82,-1:2,82,188,-1:2,188:9,102,188:5,-1,188:3,-1:3,188:2,-1,188:2," +
"-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188:2,103,-1:3,188:2,-1,188:2,-1:" +
"20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188:2,104,-1:3,188:2,-1,188:2,-1:20," +
"188:2,82,-1:2,82,188,-1:2,188:8,105,188:6,-1,188:3,-1:3,188:2,-1,188:2,-1:2" +
"0,188:2,82,-1:2,82,188,-1:2,188:7,106,188:7,-1,188:3,-1:3,188:2,-1,188:2,-1" +
":20,188:2,82,-1:2,82,188,-1:2,188:15,-1,107,188:2,-1:3,188:2,-1,188:2,-1:20" +
",188:2,82,-1:2,82,188,-1:2,188:11,108,188:3,-1,188:3,-1:3,188:2,-1,188:2,-1" +
":20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188:3,-1:3,188:2,-1,188,109,-1:20,1" +
"88:2,82,-1:2,82,188,-1:2,188:15,-1,110,188:2,-1:3,188:2,-1,188:2,-1:20,188:" +
"2,82,-1:2,82,188,-1:2,188:3,111,188:11,-1,188:3,-1:3,188:2,-1,188:2,-1:20,1" +
"88:2,82,-1:2,82,188,-1:2,188:15,-1,112,188:2,-1:3,188:2,-1,188:2,-1:20,188:" +
"2,82,-1:2,82,188,-1:2,188:4,113,188:10,-1,188:3,-1:3,188:2,-1,188:2,-1:20,1" +
"88:2,82,-1:2,82,188,-1:2,188:4,114,188:10,-1,188:3,-1:3,188:2,-1,188:2,-1:2" +
"0,188:2,82,-1:2,82,188,-1:2,188:8,115,188:6,-1,188:3,-1:3,188:2,-1,188:2,-1" +
":20,188:2,82,-1:2,82,188,-1:2,188:3,120,188:11,-1,188:3,-1:3,188:2,-1,188:2" +
",-1:20,188:2,82,-1:2,82,188,-1:2,188:11,121,188:3,-1,147,188:2,-1:3,148,188" +
",-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:3,124,188:11,-1,188:3,-1:3,18" +
"8:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188:2,154,-1:3,188:2" +
",-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,156,188:14,-1,188:3,-1:3,188:2,-1" +
",188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:11,157,188:3,-1,188:3,-1:3,188:2" +
",-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,125,188:14,-1,188:3,-1:3,188:2,-1" +
",188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:3,158,188:10,183,-1,188:3,-1:3,1" +
"88:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188,159,188,-1:3,18" +
"8:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:2,126,188:12,-1,188:3,-1:3" +
",188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:6,161,188:8,-1,188:3,-1" +
":3,188:2,-1,162,188,-1:20,188:2,82,-1:2,82,188,-1:2,188:14,127,-1,188:3,-1:" +
"3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188,163,188:13,-1,188:3,-1" +
":3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,128,188:14,-1,188:3,-1:3," +
"188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:7,164,188:7,-1,188:3,-1:" +
"3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188:3,-1:3,188,1" +
"29,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:6,130,188:8,-1,188:3,-1:3,1" +
"88:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,165,188:14,-1,188:3,-1:3,188:" +
"2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,166,188:2,-1:3,188:2,-" +
"1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:2,131,188:12,-1,188:3,-1:3,188:" +
"2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188,167,188,-1:3,188:2" +
",-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:14,132,-1,188:3,-1:3,188:2,-1" +
",188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,133,188:2,-1:3,188:2,-1,18" +
"8:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:9,168,188:5,-1,188:3,-1:3,188:2,-1," +
"188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:6,169,188:8,-1,188:3,-1:3,188:2,-" +
"1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188:2,170,-1:3,188:2,-1,1" +
"88:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:5,171,188:9,-1,188:3,-1:3,188:2,-1" +
",188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:3,134,188:11,-1,188:3,-1:3,188:2" +
",-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:14,135,-1,188:3,-1:3,188:2,-1" +
",188:2,-1:20,188:2,82,-1:2,82,188,-1:2,136,188:14,-1,188:3,-1:3,188:2,-1,18" +
"8:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188:3,-1:3,188,172,-1,188:2,-" +
"1:20,188:2,82,-1:2,82,188,-1:2,188:2,173,188:12,-1,188:3,-1:3,188:2,-1,188:" +
"2,-1:20,188:2,82,-1:2,82,188,-1:2,188:3,174,188:11,-1,188:3,-1:3,188:2,-1,1" +
"88:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:11,175,188:3,-1,188:3,-1:3,188:2,-" +
"1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:2,176,188:12,-1,188:3,-1:3,188:" +
"2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:7,177,188:7,-1,188:3,-1:3,18" +
"8:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188:2,179,-1:3,188:2" +
",-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:9,137,188:5,-1,188:3,-1:3,188" +
":2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:9,138,188:5,-1,188:3,-1:3,1" +
"88:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188:3,-1:3,188,139," +
"-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:2,181,188:12,-1,188:3,-1:3,188" +
":2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188:2,155,-1:3,188:2," +
"-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:11,160,188:3,-1,188:3,-1:3,188" +
":2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188,184,188,-1:3,188:" +
"2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:15,-1,188,192,188,-1:3,188:2" +
",-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:7,178,188:7,-1,188:3,-1:3,188" +
":2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:5,144,145,188:8,-1,188:3,-1" +
":3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:8,146,188:6,-1,188:3," +
"-1:3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:9,182,188,149,188:3" +
",-1,188:3,-1:3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:2,150,188" +
":12,-1,151,188:2,-1:3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188:11" +
",185,188:3,-1,188:3,-1:3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1:2,188" +
":2,191,188:12,-1,188:3,-1:3,188:2,-1,188:2,-1:20,188:2,82,-1:2,82,188,-1");

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
						{return new Symbol(sym.CC,yyline,yychar, yytext());}
					case -4:
						break;
					case 4:
						{return new Symbol(sym.DOSP,yyline,yychar, yytext());}
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
						{return new Symbol(sym.PARIZQ,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(sym.PTCOMA,yyline,yychar, yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.CORIZQ,yyline,yychar, yytext());}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.CORDER,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.LLAVEIZQ,yyline,yychar, yytext());}
					case -13:
						break;
					case 13:
						{return new Symbol(sym.LLAVEDER,yyline,yychar, yytext());}
					case -14:
						break;
					case 14:
						{return new Symbol(sym.COMA,yyline,yychar, yytext());}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.MAS,yyline,yychar, yytext());}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.MENOS,yyline,yychar, yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.POR,yyline,yychar, yytext());}
					case -18:
						break;
					case 18:
						{return new Symbol(sym.DIVIDIDO,yyline,yychar, yytext());}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.POTENCIA,yyline,yychar, yytext());}
					case -20:
						break;
					case 20:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -21:
						break;
					case 21:
						{return new Symbol(sym.MENOR,yyline,yychar, yytext());}
					case -22:
						break;
					case 22:
						{return new Symbol(sym.INTERROGACION,yyline,yychar, yytext());}
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
						{return new Symbol(sym.IF,yyline,yychar, yytext());}
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
						{return new Symbol(sym.TRUNK,yyline,yychar, yytext());}
					case -49:
						break;
					case 49:
						{return new Symbol(sym.BREAK,yyline,yychar, yytext());}
					case -50:
						break;
					case 50:
						{return new Symbol(sym.ROUND,yyline,yychar, yytext());}
					case -51:
						break;
					case 51:
						{return new Symbol(sym.PRINT,yyline,yychar, yytext());}
					case -52:
						break;
					case 52:
						{return new Symbol(sym.LENGHT,yyline,yychar, yytext());}
					case -53:
						break;
					case 53:
						{return new Symbol(sym.SWITCH,yyline,yychar, yytext());}
					case -54:
						break;
					case 54:
						{return new Symbol(sym.TYPEOF,yyline,yychar, yytext());}
					case -55:
						break;
					case 55:
						{return new Symbol(sym.RETURN,yyline,yychar, yytext());}
					case -56:
						break;
					case 56:
						{return new Symbol(sym.REMOVE,yyline,yychar, yytext());}
					case -57:
						break;
					case 57:
						{return new Symbol(sym.LENGHTPAR,yyline,yychar, yytext());}
					case -58:
						break;
					case 58:
						{return new Symbol(sym.BARPLOT,yyline,yychar, yytext());}
					case -59:
						break;
					case 59:
						{return new Symbol(sym.DEF,yyline,yychar, yytext());}
					case -60:
						break;
					case 60:
						{return new Symbol(sym.FUNCTION,yyline,yychar, yytext());}
					case -61:
						break;
					case 61:
						{return new Symbol(sym.DEFAULT,yyline,yychar, yytext());}
					case -62:
						break;
					case 62:
						{return new Symbol(sym.TOLOWERCASE,yyline,yychar, yytext());}
					case -63:
						break;
					case 63:
						{return new Symbol(sym.TOUPPERCASE,yyline,yychar, yytext());}
					case -64:
						break;
					case 64:
						{return new Symbol(sym.STRINGLENGHT,yyline,yychar, yytext());}
					case -65:
						break;
					case 65:
						{return new Symbol(sym.STRINGLENGHTPAR,yyline,yychar, yytext());}
					case -66:
						break;
					case 67:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -67:
						break;
					case 68:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -68:
						break;
					case 69:
						{}
					case -69:
						break;
					case 70:
						{return new Symbol(sym.CADENA,yyline,yychar, (yytext()).substring(1,yytext().length()-1));}
					case -70:
						break;
					case 71:
						{}
					case -71:
						break;
					case 73:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -72:
						break;
					case 74:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -73:
						break;
					case 75:
						{}
					case -74:
						break;
					case 76:
						{}
					case -75:
						break;
					case 78:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -76:
						break;
					case 79:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -77:
						break;
					case 80:
						{}
					case -78:
						break;
					case 82:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -79:
						break;
					case 83:
						{
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}
					case -80:
						break;
					case 85:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -81:
						break;
					case 87:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -82:
						break;
					case 89:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -83:
						break;
					case 91:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -84:
						break;
					case 93:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -85:
						break;
					case 95:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -86:
						break;
					case 97:
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
					case 135:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -123:
						break;
					case 136:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -124:
						break;
					case 137:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -125:
						break;
					case 138:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -126:
						break;
					case 139:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -127:
						break;
					case 140:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -128:
						break;
					case 141:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -129:
						break;
					case 142:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -130:
						break;
					case 143:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -131:
						break;
					case 144:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -132:
						break;
					case 145:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -133:
						break;
					case 146:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -134:
						break;
					case 147:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -135:
						break;
					case 148:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -136:
						break;
					case 149:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -137:
						break;
					case 150:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -138:
						break;
					case 151:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -139:
						break;
					case 152:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -140:
						break;
					case 153:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -141:
						break;
					case 154:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -142:
						break;
					case 155:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -143:
						break;
					case 156:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -144:
						break;
					case 157:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -145:
						break;
					case 158:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -146:
						break;
					case 159:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -147:
						break;
					case 160:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -148:
						break;
					case 161:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -149:
						break;
					case 162:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -150:
						break;
					case 163:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -151:
						break;
					case 164:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -152:
						break;
					case 165:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -153:
						break;
					case 166:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -154:
						break;
					case 167:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -155:
						break;
					case 168:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -156:
						break;
					case 169:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -157:
						break;
					case 170:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -158:
						break;
					case 171:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -159:
						break;
					case 172:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -160:
						break;
					case 173:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -161:
						break;
					case 174:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -162:
						break;
					case 175:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -163:
						break;
					case 176:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -164:
						break;
					case 177:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -165:
						break;
					case 178:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -166:
						break;
					case 179:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -167:
						break;
					case 180:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -168:
						break;
					case 181:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -169:
						break;
					case 182:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -170:
						break;
					case 183:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -171:
						break;
					case 184:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -172:
						break;
					case 185:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -173:
						break;
					case 186:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -174:
						break;
					case 187:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -175:
						break;
					case 188:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -176:
						break;
					case 189:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -177:
						break;
					case 190:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -178:
						break;
					case 191:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -179:
						break;
					case 192:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -180:
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



import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexica {
	final static int STR = 40;
	final static int CHR = 41;
	final static int COMMAR = 28;// ,
	final static int COLON = 29;// :
	final static int DEFINE = 98;//define
	final static int INCLUDE = 99;//include
	final static int DEFINE_NUM = 97;
	final static int RIGHT_PARE = 11;// )
	final static int LEFT_PARE = 10;// (
	final static int RIGHT_BRACE = 15;// }
	final static int LEFT_BRACE = 14;// {
	final static int RIGHT_BRAC = 13;// ]
	final static int LEFT_BRAC = 12;// [
	final static int LOGIC= 5;// || &&
	final static int LOGIC_OP= 4;// < <= > >= <> == 
	final static int SEMI= 27;// ;
	final static int SIGN= 9;
	final static int TYPE= 34;
	final static int INT= 35;
	final static int INT_ARR= 36;
	final static int FLOAT= 37;
	final static int FLOAT_ARR= 38;
	final static int DOUBLE= 39;
	final static int DOUBLE_ARR= 42;
	final static int CHAR= 43;
	final static int CHAR_ARR= 44;
	final static int UNKNOW_TYPE= 45;
	final static int QUOT= 6;
	final static int HEAD_FILE = 60;
	final static int NUMBER_INT = 50;
	final static int NUMBER_FLOAT = 51;
	final static int OPERATOR = 3;//+ - * /
	final static int ASSI = 20;// =
	final static int MONO_OPT = 2;//++ --
	final static int LINE_COM = 30;
	final static int OPERATOR_ASSI = 16;//+= -= *= /=
	final static int LEFT_AREA_COM = 31;
	final static int RIGHT_AREA_COM = 32;
	final static int RETRIVED = 33;
	final static int UNKNOW = 100;
	final static int ADDRESS = 7;
	final static int FUNCTION = 61;
	public String lexeme[] = new String[10000];
	public int token[] = new int[10000];
	public int lex_num = 0, line_flag = 0;
	public String code = "";
  public int get_lex(String code)
  {
	  
	  int code_pt = 0, flag = 0,is_q = 0;
	  //String path = "C:\\Users\\yaozo\\Desktop\\code.txt";
	  int c;
	  //code = this.read(path);
	  lexeme[0]="";
	  token[0] = 0;
	  lex_num = 0;
	  line_flag = 0;
	  while (code_pt < code.length())
	  {
		  if ((is_q == 1&&code.charAt(code_pt)!='\"')||(is_q == 2&&code.charAt(code_pt)!='\'') || code.charAt(code_pt) == '.' || code.charAt(code_pt) == '_' || (code.charAt(code_pt)<='z'&&code.charAt(code_pt)>='a') || (code.charAt(code_pt)<='Z'&&code.charAt(code_pt)>='A') || (code.charAt(code_pt)<='9'&&code.charAt(code_pt)>='0'))
		  {
			  if (flag == 1 && lexeme[lex_num].length()>0) {this.get_token();lex_num++;lexeme[lex_num]="";flag = 0;}
			  lexeme[lex_num] += code.charAt(code_pt);
		  }else
		  {
			  if (lexeme[lex_num].equals("")) lex_num--;
			  if(code.charAt(code_pt) != ' ' && code.charAt(code_pt) != '\t' && code.charAt(code_pt) != '\r' && code.charAt(code_pt) !='\n')
			  {
				  if (flag == 0 || (flag == 1 && lexeme[lex_num].length()==2))
				  {
					  if (code.charAt(code_pt) == '\"')
						  if (is_q ==0) {is_q = 1;this.get_token();}
						  else {is_q = 0;token[lex_num] = STR;}
					  else
					  if (code.charAt(code_pt) == '\'')
						  if (is_q ==0) {is_q = 2;this.get_token();}
						  else {is_q = 0;token[lex_num] = CHR;}
					  else this.get_token();
					 lexeme[++lex_num]="";
					 lexeme[lex_num] += code.charAt(code_pt);
					 flag = 1;
				  }else
				  {
					  switch(code.charAt(code_pt))
					  {
					    case '-':
					    case '+':
					    case '&':
					    case '<':
					    case '>':
					    case '|':
					    	if (lexeme[lex_num].charAt(0) == code.charAt(code_pt))
					    	{
					    		lexeme[lex_num] += code.charAt(code_pt);
					    	}else{
								  if (code.charAt(code_pt) == '\"')
									  if (is_q ==0) {is_q = 1;this.get_token();}
									  else {is_q = 0;token[lex_num] = STR;}
								  else
								  if (code.charAt(code_pt) == '\'')
									  if (is_q ==0) {is_q = 2;this.get_token();}
									  else {is_q = 0;token[lex_num] = CHR;}
								  else this.get_token();
								  lexeme[++lex_num]="";
								  lexeme[lex_num] += code.charAt(code_pt);
								  flag = 1;
					    	}
					    	break;
					    case '*':
					    case '/':
					    	if (lexeme[lex_num].charAt(0) == '/' || lexeme[lex_num].charAt(0) == '*')
					    	{
					    		lexeme[lex_num] += code.charAt(code_pt);
					    	}else{
								  if (code.charAt(code_pt) == '\"')
									  if (is_q ==0) {is_q = 1;this.get_token();}
									  else {is_q = 0;token[lex_num] = STR;}
								  else
								  if (code.charAt(code_pt) == '\'')
									  if (is_q ==0) {is_q = 2;this.get_token();}
									  else {is_q = 0;token[lex_num] = CHR;}
								  else this.get_token();
								  lexeme[++lex_num]="";
								  lexeme[lex_num] += code.charAt(code_pt);
								  flag = 1;
					    	}
					    	break;
					    case '=':
					    	switch(lexeme[lex_num].charAt(0))
					    	{
					    	case '!':
					    	case '=':
					    	case '<':
					    	case '>':
					    	case '+':
					    	case '-':
					    	case '*':
					    	case '/':
						    		lexeme[lex_num] += code.charAt(code_pt);
						    		break;
					    	default:
								  if (code.charAt(code_pt) == '\"')
									  if (is_q ==0) {is_q = 1;this.get_token();}
									  else {is_q = 0;token[lex_num] = STR;}
								  else
								  if (code.charAt(code_pt) == '\'')
									  if (is_q ==0) {is_q = 2;this.get_token();}
									  else {is_q = 0;token[lex_num] = CHR;}
								  else this.get_token();
								  lexeme[++lex_num]="";
								  lexeme[lex_num] += code.charAt(code_pt);
								  flag = 1;
					    	}
				    		break;
					    default:
							  if (code.charAt(code_pt) == '\"')
								  if (is_q ==0) {is_q = 1;this.get_token();}
								  else {is_q = 0;token[lex_num] = STR;}
							  else
							  if (code.charAt(code_pt) == '\'')
								  if (is_q ==0) {is_q = 2;this.get_token();}
								  else {is_q = 0;token[lex_num] = CHR;}
							  else this.get_token();
							  lexeme[++lex_num]="";
							  lexeme[lex_num] += code.charAt(code_pt);
							  flag = 1;
					  }
				  }
			  }else{
				  if (line_flag == 1 && code.charAt(code_pt) == '\n') line_flag = 0;
				  flag = 1;
			  }
		  }
		  code_pt++;
	  }get_token();
	  retoken();
	return 0;
  }
  public static String read(String filePath)
  {
      StringBuffer txtContent = new StringBuffer();
      byte[] b = new byte[1];
      InputStream in = null;
      try
      {
          in = new FileInputStream(filePath);
          while (in.read(b) != -1)
          {
              txtContent.append(new String(b));
          }
          in.close();
      }
      catch (FileNotFoundException e)
      {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      catch (IOException e)
      {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      finally
      {
          if (in != null)
          {
              try
              {
                  in.close();
              }
              catch (IOException e)
              {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }
          }
      }
      return txtContent.toString();
  }
  public int get_token()
  {
	  String retrived[]= new String[]{"auto","break","NULL","printf","scanf","case","const","continue","default","do","else","enum","extern","for","goto","if","redister","return","signed","sizeof","static","struct","switch","typedef","union","unsigned","void","volatile","while"};
	  String type[] = new String[]{"char","double","float","int","long","short"};
	  int ret_num = 29,counter,type_num=6;
	  String tmp = "/";
	  tmp = tmp + "/";
	  if (lex_num<0) return 0;
	  if (line_flag == 1) 
	  {
		  token[lex_num] = LINE_COM;
		  return 0;
	  }
	  token[lex_num] = UNKNOW; 
		  switch(lexeme[lex_num])
		  {
		  case "define" :
			  token[lex_num] = DEFINE;break;
		  case "include":
			  token[lex_num] = INCLUDE;break;
		  case "/*":
			  token[lex_num] = LEFT_AREA_COM;break;
		  case "*/":
			  token[lex_num] = RIGHT_AREA_COM;break;
		  case "++":
		  case "--":
			  token[lex_num] = MONO_OPT;break;
		  case "(":
			  token[lex_num] = LEFT_PARE;break;
		  case ")":
			  token[lex_num] = RIGHT_PARE;break;
		  case "[":
			  token[lex_num] = LEFT_BRAC;break;
		  case "]":
			  token[lex_num] = RIGHT_BRAC;break;
		  case "{":
			  token[lex_num] = LEFT_BRACE;break;
		  case "}":
			  token[lex_num] = RIGHT_BRACE;break;
		  case "=":
			  token[lex_num] = ASSI;break;
		  case "||":
		  case "&&":
			  token[lex_num] = LOGIC;break;
		  case "<":
		  case "<=":
		  case ">":
		  case ">=":
		  case "<>":
		  case "==":
		  case "!=":
			  token[lex_num] = LOGIC_OP;break;
		  case "+":
		  case "-":
		  case "*":
		  case "/":
		  case "%":
			  token[lex_num] = OPERATOR;break;
		  case "+=":
		  case "-=":
		  case "*=":
		  case "/=":
			  token[lex_num] = OPERATOR_ASSI;break;
		  case "&":
			  token[lex_num] = ADDRESS;break;
		  case ";":
			  token[lex_num] = SEMI;break;
		  case "//":
			  line_flag = 1;
			  token[lex_num] = LINE_COM;break;
		  case "#":
		  case "<<":
			  token[lex_num] = SIGN;break; 
		  case ",":
			  token[lex_num] = COMMAR;break;
		  case ":":
			  token[lex_num] = COLON;break;
		  case "\"":
		  case "\'":
			  token[lex_num] = QUOT;break;
		  default:
			  token[lex_num] = UNKNOW;
			  if (this.isInt(lexeme[lex_num])== true)
				  token[lex_num] = NUMBER_INT;
			  else
				  if (this.isFloat(lexeme[lex_num])== true)
					  token[lex_num] = NUMBER_FLOAT;
			  else
			  {
				  for (counter = 0;counter<ret_num;counter++)
				  {
					  if (lexeme[lex_num].equals(retrived[counter]))
					  {
						  token[lex_num] = RETRIVED;
					  }
				  }
				  for (counter = 0;counter<type_num;counter++)
				  {
					  if (lexeme[lex_num].equals(type[counter]))
					  {
						  token[lex_num] = TYPE;
					  }
				  }
				  if (token[lex_num-1] == DEFINE)
				  {
					  token[lex_num] = DEFINE_NUM;
				  }
			  }
		  }
	  return 0;
  }
  public int retoken()
  {
	  int counter,counter2,counter3=0;
	  int line_com[] = new int[1000];
	  for (counter=0;counter<lex_num;counter++)
	  {
		  if (counter>=1)
		  if (token[counter-1] == LINE_COM && token[counter]!= LINE_COM) line_com[counter3++] = counter;
		  if (token[counter] == UNKNOW && token[counter-3] == SIGN && token[counter - 2] == INCLUDE && token[counter -1] == LOGIC_OP && token[counter + 1]==LOGIC_OP)
			  token[counter] = HEAD_FILE;
		  else
			  if (token[counter] == TYPE && token[counter+2]!= LEFT_PARE)
			  {
				  for (counter2=counter+1;token[counter2]!=SEMI && token[counter2]!=RIGHT_PARE;counter2++)
				  {
					  switch(lexeme[counter])
					  {
					  case "int":
						  if (token[counter2] == UNKNOW) 
						  {
							  if (token[counter2+1] == LEFT_BRAC) {token[counter2] = INT_ARR;change(counter2,INT_ARR);} else
							  {token[counter2] = INT;
							  change(counter2,INT);}
						  }
						  break;
					  case "double":
						  if (token[counter2] == UNKNOW) 
						  {
							  if (token[counter2+1] == LEFT_BRAC) {token[counter2] = DOUBLE_ARR;change(counter2,DOUBLE_ARR);} else
							  {token[counter2] = DOUBLE;
							  change(counter2,DOUBLE);}
						  }
						  break;
					  case "float":
						  if (token[counter2] == UNKNOW) 
						  {
							  if (token[counter2+1] == LEFT_BRAC) {token[counter2] = FLOAT_ARR;change(counter2,FLOAT_ARR);} else
							  {token[counter2] = FLOAT;
							  change(counter2,FLOAT);}
						  }
						  break;
					  case "char":
						  if (token[counter2] == UNKNOW) 
						  {
							  if (token[counter2+1] == LEFT_BRAC) {token[counter2] = CHAR_ARR;change(counter2,CHAR_ARR);} else
							  {token[counter2] = CHAR;
							  change(counter2,CHAR);}
						  }
						  break;
					  default:
						  if (token[counter2] == UNKNOW) 
						  {
							  token[counter2] = UNKNOW_TYPE;
							  change(counter2,UNKNOW_TYPE);
						  }
					  }
				  }
			  }else
			  {
				  if ((token[counter] == TYPE || lexeme[counter].equals("void")) && token[counter+1] == UNKNOW)
				  {
					  token[counter+1] = FUNCTION;
					  change(counter+1,FUNCTION);
				  }
			  }
		  
	  }
	  for (counter2=0;counter2<counter3;counter2++)
	  token[line_com[counter2]] = LINE_COM;
	  return 0;
  }
  public int change(int counter,int dest)
  {
	  int counter2;
	  for (counter2=counter+1;counter2<lex_num;counter2++)
	  {
		  if (lexeme[counter2].equals(lexeme[counter]))
		  {
			  token[counter2] = dest;
		  }
	  }
  return 0;
  }
  public static boolean isInt(String str){
	  for (int i = 0; i < str.length(); i++){
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
  }
  public static boolean isFloat(String str){
	  int dot=0;
	  for (int i = 0; i < str.length(); i++){
		   if (!Character.isDigit(str.charAt(i)) && str.charAt(i)!='.'){
			   if (str.charAt(i)=='.') dot++;
		    return false;
		   }
		  }
	  if (dot>=2) return false;
	  else
		  return true;
  }
}

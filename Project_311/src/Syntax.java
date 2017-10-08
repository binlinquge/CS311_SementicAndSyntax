
public class Syntax {
	public int lex_num;
    /*public int syntax(String[] lexeme, int[] token, int lex_num1)
    {
    	int counter=0;
    	int result_info;
    	lex_num = lex_num1;
    	//while (counter != lex_num)
    	{
    		switch (lexeme[counter])
    		{
    		case "#":
    			if (token[counter+1] == 99){ 
    				if ((result_info=check_include(lexeme,token,counter))!=-1)
    					return result_info;
        			counter += 4;
    				}
    			else if (token[counter+1] == 98){
    				if ((result_info=check_define(lexeme,token,counter))!=-1)
    					return result_info;
    				counter += 3;
    			}
    			break;
    		//case ""
    		}
    		counter++;
    	}
    	System.out.println(lexeme[2]+token[2]);
    return -1;
    }*/
    public int check_include(String[] lexeme, int[] token, int counter)
    {
    	if (!lexeme[counter+2].equals("<")) return counter+2;
    	if (token[counter+3] != 60) return counter+3;
    	if (!lexeme[counter+4].equals(">")) return counter+4;
    	return -1;
    }
    public int check_define(String[] lexeme, int[] token, int counter)
    {
    	if (token[counter+2] != 97) return counter+2;
    	return -1;
    }
    public int check_scanf(int[] token,int counter)
    {
    	counter++;
    	if (token[counter] == 10)
    	{
    		counter++;
    		if (token[counter] == 6)
    		{
    			counter++;
    			if (token[counter] == 40)
    			{
    				counter++;
    				if (token[counter] == 6)
    				{
    					counter++;
    					while(token[counter]!=11)
    					{
    						if (token[counter] == 28) counter++;
    						else return counter;
    						switch(token[counter])
    	            		{
    	            		case 35:
    	            		case 37:
    	            		case 39:
    	            		case 43:
    	            		case 45:
    	            		case 41:
    	            		case 50:
    	            		case 51:
    	            			counter++;
    	            			break;
    	            		case 36:
    	            		case 38:
    	            		case 42:
    	            		case 44:
    	            			if (token[counter+1] == 12 && (token[counter+2] == 50 || token[counter+2] == 35) && token[counter+3] == 13)
    	            				counter+=4;
    	            			else return counter;
    	            			break;
    	            		default:
    	            			return counter;
    	            		}
    					}
    					counter++;
    					if (token[counter] == 27)
    					{
    						return -1;
    					}else return counter;
    				}else return counter;
    			}else return counter;
    		}else return counter;
    	}else return counter;
    }
    public int check_if(String[] lexeme, int[] token, int counter, int brace_num)
    {
    	int result_info = -1;
    	counter++;
    	if (token[counter] == 10)
    	{
    		counter++;
    		result_info = judgement(token,counter);
    		if (result_info < 0)
    		{
    			counter = 0-result_info;
    			if (token[counter] == 11 || token[counter-1] == 11)
    			{
    				counter++;
    				while (!lexeme[counter].equals("else"))
    				{
    					if (token[counter] == 14) brace_num++;
    					if (token[counter] == 15) brace_num--;
    					if (brace_num == 0) return 0-counter;
    					counter++;
    				}return 0-counter;
    			}else return counter;
    		}else return result_info;
    	}else return counter;
    }
    public int check_switch(String[] lexeme,int[] token,int counter)
    {
    	int result_info = -1;
    	counter++;
    	if (token[counter] == 10)
    	{
    		counter++;
    		switch(token[counter])
    		{
    		case 35://int
    		case 43://char
    			counter++;
    			break;
    		case 36://int[]
    		case 44://char[]
    			if (token[counter+1] == 12 && (token[counter+2] == 50 || token[counter+2] == 35) && token[counter+3] == 13)
    				counter+=4;
    			else return counter;
    			break;
    		default:
    			return counter;
    		}
    		if (token[counter] == 11)
    		{
    			counter++;
    			if (token[counter] == 14)
    			{
					result_info = check_brace(token,counter);
					if (result_info < 0)
					{
						counter++;
						result_info = check_case(lexeme,token,counter,1);
						if (result_info<0) return -1;
						else return result_info;
					}else return result_info;
    			}else return counter;
    		}else return counter;
    	}else return counter;
    }
    private int check_case(String[] lexeme,int[] token, int counter, int brace_num)
    {
    	if (lexeme[counter].equals("case"))
    	{
    		counter++;
    		if (token[counter] == 50 || token[counter] == 6)
    		{
    			if (token[counter] == 6)
    			{
    				if (token[counter+1] == 41 && token[counter+2] == 6)
    					counter+=2;
    				else return counter+1;
    			}
    			counter++;
    			if (token[counter] == 29)
    			{
    				counter++;
    				while(!lexeme[counter].equals("case"))
    				{
    					if (token[counter] == 14) brace_num++;
    					if (token[counter] == 15) brace_num--;
    					if (brace_num == 0) return 0-counter;
    					counter++;
    				}
    				return check_case(lexeme,token,counter,brace_num);
    			}else return counter;
    		}else return counter;
    	}else return counter;
    }
    public int check_while(int[] token,int counter)
    {
    	int result_info = -1;
    	counter++;
    	if (token[counter] == 10)
    	{
    		counter++;
    		result_info = judgement(token,counter);
    		if (result_info < 0)
    		{
    			counter = 0-result_info;
    			if (token[counter] == 11)
    			{
						counter++;
						if (token[counter] == 27)
						{
							return -1;
						}else{
							if (token[counter] == 14)
							{
								result_info = check_brace(token,counter);
								if (result_info < 0) return -1;
								else return result_info;
							}else return -1;
						}
    			}else return counter;
    		}else return result_info;
    	}else return counter;
    }
    public int check_for(int[] token, int counter)
    {
    	int result_info = -1;
    	counter++;
    	if (token[counter] == 10)
    	{
    		counter++;
    		result_info = primise(token,counter);
    		if (result_info < 0)
    		{
    			counter=0-result_info;
    			if (token[counter] == 27)
    			{
    				counter++;
    				result_info = judgement(token,counter);
    				if (result_info < 0)
    				{
    					counter = 0-result_info;
    					if (token[counter] == 27)
    					{
    						counter++;
    						result_info = primise(token,counter);
    						if (result_info < 0)
    						{
    							counter = 0-result_info;
    							if (token[counter] == 11)
    							{
    								counter++;
    								if (token[counter] == 27)
    								{
    									return -1;
    								}else{
    									if (token[counter] == 14)
    									{
    										result_info = check_brace(token,counter);
    										if (result_info < 0) return -1;
    										else return result_info;
    									}else return -1;
    								}
    							}else return counter;
    						}else return result_info;
    					}else return counter;
    				}else return result_info;
    			}else return counter;
    		}else return result_info;
    	}else return counter;
    }
    private int primise(int[] token, int counter)
    {
    	//return 0-counter-3;
    	if (token[counter]==27) return 0-counter;
    	else{
    		switch(token[counter])
    		{
    		case 35:
    		case 37:
    		case 39:
    		case 43:
    		case 45:
    			counter++;
    			break;
    		case 36:
    		case 38:
    		case 42:
    		case 44:
    			if (token[counter+1] == 12 && (token[counter+2] == 50 || token[counter+2] == 35) && token[counter+3] == 13)
    				counter+=4;
    			else return counter;
    			break;
    		default:
    			return counter;
    		}
    		switch(token[counter])
    		{
    		case 20:
        		while(true)
        		{
        			counter++;
            		switch(token[counter])
            		{
            		case 35:
            		case 37:
            		case 39:
            		case 43:
            		case 45:
            		case 41:
            		case 50:
            		case 51:
            			counter++;
            			break;
            		case 36:
            		case 38:
            		case 42:
            		case 44:
            			if (token[counter+1] == 12 && (token[counter+2] == 50 || token[counter+2] == 35) && token[counter+3] == 13)
            				counter+=4;
            			else return counter;
            			break;
            		default:
            			return counter;
            		}
            		if (token[counter] == 3) counter=counter;
            		else if (token[counter] == 27 || token[counter] == 28 || token[counter] == 11)
            		{
            			counter++;
            			break;
            		}else return counter;
        		}
        		break;
    		case 2:
    			counter++;
    			counter++;
    			break;
    		case 16:
    			counter++;
    			switch(token[counter])
        		{
        		case 35:
        		case 37:
        		case 39:
        		case 43:
        		case 45:
        		case 41:
        		case 50:
        		case 51:
        			counter++;
        			break;
        		case 36:
        		case 38:
        		case 42:
        		case 44:
        			if (token[counter+1] == 12 && (token[counter+2] == 50 || token[counter+2] == 35) && token[counter+3] == 13)
        				counter+=4;
        			else return counter;
        			break;
        		default:
        			return counter;
        		}
    			if (token[counter] == 27 || token[counter] == 28 || token[counter] == 11)
        		{
        			counter++;
        			break;
        		}else return counter;
			default:
    			return counter;
    		}
    		if (token[counter-1] == 11)
    		{
    			counter--;
    			return 0-counter;
    		}else
    		if (token[counter-1] == 27)
    		{
    			counter--;
    			return 0-counter;
    		}else if (token[counter-1] == 28)
    		{
    			if (token[counter] == 11 || token[counter] == 27) return counter;else
    			return primise(token,counter);
    		}else return counter;
    	}
    }
    private int judgement(int[] token, int counter)
    {
    	int result_info=-1;
		while((token[counter] == 10 || token[counter] == 11) && token[counter+1] !=14 ) counter++;
    	if (token[counter]==27) return 0-counter;
    	else{
	    	result_info = judge(token,counter);
	    	if (result_info<0)
	    	{
	    		counter = 0-result_info;
	    		while((token[counter] == 10 || token[counter] == 11) && token[counter+1] !=14 ) counter++;
	    		if (token[counter] == 5)
	    		{
	    			counter++;
	    			return judgement(token,counter);
	    		}else if (token[counter] == 27 || token[counter] == 11) return 0-counter;
	    		else return counter;
	    	}else return result_info;
    	}
    }
    private int judge(int[] token,int counter)
    {
    	while(true)
		{
    		while((token[counter] == 10 || token[counter] == 11) && token[counter+1] !=14 ) counter++;
    		switch(token[counter])
    		{
    		case 35:
    		case 37:
    		case 39:
    		case 43:
    		case 45:
    		case 41:
    		case 50:
    		case 51:
    			counter++;
    			break;
    		case 36:
    		case 38:
    		case 42:
    		case 44:
    			if (token[counter+1] == 12 && (token[counter+2] == 50 || token[counter+2] == 35) && token[counter+3] == 13)
    				counter+=4;
    			else return counter;
    			break;
    		default:
    			return counter;
    		}
    		while((token[counter] == 10 || token[counter] == 11) && token[counter+1] !=14 ) counter++;
    		if (token[counter] == 3) counter++;
    		else if (token[counter] == 27 || token[counter] == 5 || token[counter] == 11)
    		{
    			return 0-counter;
    		}else if (token[counter] == 4)
    		{
    			counter++;
    			return judge(token,counter);
    		}else return counter;
		}
    }
    private int check_brace(int[] token, int counter)
    {
    	return -1;
    }
}

package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SplitTag extends SimpleTagSupport {
	
	private String ex;
	private String[] params;

	public String getEx() {
		return ex;
	}


	public void setEx(String ex) {
		this.ex = ex;
	}


	public String[] getParams() {
		return params;
	}


	public void setParams(String[] params) {
		this.params = params;
	}


	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext)getJspContext();
		JspWriter out = pageContext.getOut();
		int total = 0;
		if(params.length>0) {
			for(int i=0; i<params.length; i++) {
				String param = params[i];
				if(param.split(ex).length>1) {
					int price = Integer.parseInt(param.split(ex)[1]);
					total += price;
				} else {
					total = Integer.parseInt(param);
				}
			}
		}
		out.println(total);
	}
}

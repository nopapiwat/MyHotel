import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchHotel
 */

@WebServlet("/search")
public class SearchHotel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Set<String> banSession;
	Search search;
	
	int rateLimit = 5;
	int callNum;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		if(!checkSession(session,out)){
			return;
		}
		
		String city = request.getParameter("city");
		String order = request.getParameter("order");
		
		out.println(search.search(city, order));
	}
	
	private boolean checkSession(HttpSession session,PrintWriter out ){
		//out.println(session.getId());
		long now = System.currentTimeMillis();
		long lastAcc = session.getLastAccessedTime();
		if(banSession.contains(session.getId())){
			if(now-(long)session.getAttribute("banTime")>=5*60*1000){
				banSession.remove(session.getId());
				callNum = 0;
			}else{
				//out.println(now-(long)session.getAttribute("banTime"));
				out.println("This session is ban");
				return false;
			}
		}		
		//out.println(now-lastAcc);
		if (session.isNew() || now - lastAcc >= 10*1000) {
			callNum = 0;
			//out.println("Reset");
		}
		callNum += 1;
		out.println("Access Time : "+callNum);
		if(callNum > rateLimit){
			out.println("ban this session");
			session.setAttribute("banTime", now);
			banSession.add(session.getId());
			return false;
		}
		return true;
	}

	@Override
	public void init() throws ServletException {
		banSession = new TreeSet<String>();
		search = new Search();
		
	}

	public void destroy() {
		super.destroy();
	}

}
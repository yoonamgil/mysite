package com.douzone.mysite.action.guestbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class GuestBookTestAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		File tempdir = new File("/var/www/git/test02" + ".git");

		 String command = "sudo chown -R apache.apache /var/www/git/test02.git";  
		
		try {

			Git git = Git.init().setDirectory(tempdir).setBare(true).call();

			assertTrue(git.getRepository().isBare());

			assertEquals(tempdir, git.getRepository().getDirectory());
			 shellCmd(command);

		} catch (IllegalStateException e) {

			e.printStackTrace();

		} catch (GitAPIException e) {

			e.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			

		}
		
		WebUtil.redirect(request.getContextPath()+"/guestbook?a=list", request, response);
	}

	 public static void shellCmd(String command) throws Exception {
         Runtime runtime = Runtime.getRuntime();
         Process process = runtime.exec(command);
         InputStream is = process.getInputStream();
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr);
         String line;
         while((line = br.readLine()) != null) {
                        System.out.println(line);
         }



	 }
}

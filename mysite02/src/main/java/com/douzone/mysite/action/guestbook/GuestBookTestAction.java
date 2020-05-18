package com.douzone.mysite.action.guestbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

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



		try {

			Git git = Git.init().setDirectory(tempdir).setBare(true).call();

			assertTrue(git.getRepository().isBare());

			assertEquals(tempdir, git.getRepository().getDirectory());
		

		} catch (IllegalStateException e) {

			e.printStackTrace();

		} catch (GitAPIException e) {

			e.printStackTrace();

		} finally {



		}
		
		WebUtil.redirect(request.getContextPath()+"/guestbook?a=list", request, response);
	}

}

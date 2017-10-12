package fr.jabbytechs.hackathon.galaxygop;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class ServerLauncher {

	public static void main(String[] args) throws Exception {

		ResourceConfig config = new ResourceConfig();
		config.packages("fr.jabbytechs.hackathon.galaxygop.resources");
		
		ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));
		jerseyServlet.setInitOrder(0);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		context.addServlet(jerseyServlet, "/*");

		Server jettyServer = new Server(8080);
		jettyServer.setHandler(context);
		jettyServer.start();
		jettyServer.join();

	}
}

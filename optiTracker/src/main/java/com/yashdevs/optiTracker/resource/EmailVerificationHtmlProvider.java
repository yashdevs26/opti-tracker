package com.yashdevs.optiTracker.resource;

public class EmailVerificationHtmlProvider {

	public static StringBuilder provide(String location) {
		StringBuilder message = new StringBuilder();

		//message.append("<!DOCTYPE html>");
		message.append("<html>");
		message.append("<head>");
		message.append("<style>");
		message.append(".button { ");
		message.append("	border: none; ");
		message.append("	color: white; ");
		message.append("	padding: 15px 32px;   ");
		message.append("	text-align: center;   ");
		message.append("	text-decoration: none;");
		message.append("	display: inline-block;");
		message.append("	font-size: 16px;");
		message.append("	margin: 4px 2px;");
		message.append("	cursor: pointer;");
		message.append("}");
		message.append(".button2 {background-color: #ca9bf7;} ");
		message.append("</style>");
		message.append("</head>");
		message.append("<body>");
		message.append("<h3>Hello,</h3>");
		message.append(
				"<h3><p>Thank you For Registering with Opti Tracker. Please click on the button below to verify your email address with us in order to use the app<p></h3>");
		message.append("<form action=").append(location).append(" method = POST").append(">");
		message.append("<input type=").append("submit").append(" class=").append(" button button2").append(" value=").append("verify email").append(">");
		message.append("</form> ");
		message.append("<h3><p> Regards, <br /> The Opti Tracker Team </p></h3>");
		message.append("</body> ");
		message.append("</html> ");

		return message;

	}
}

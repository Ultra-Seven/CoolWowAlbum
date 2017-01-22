<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="org.apache.commons.fileupload.util.*"%>
<%@ page import="com.fudan2015.biz.impl.UserBizImpl"%>
<%@ page import="com.fudan2015.entity.User"%> 
<%@ page import="com.alibaba.fastjson.*"%>
<%
String contentType = request.getContentType();

if ( contentType.indexOf("multipart/form-data") >= 0 )
{
	Result result = new Result();
	result.avatarUrls = new ArrayList();
	result.success = false;
	result.msg = "Failure!";

	String userid;
	String username;

	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	FileItemIterator fileItems = upload.getItemIterator(request);

	int avatarNumber = 1;
	//use random number to avoid file name repetition
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS"); 
	String fileName = simpleDateFormat.format(new Date());
	Random random = new Random();
	String randomCode = "";
	for ( int i = 0; i < 8; i++ )
	{
		randomCode += Integer.toString(random.nextInt(36), 36);
	}
	fileName = fileName + randomCode;

	String initParams = "";
	BufferedInputStream	inputStream;
	BufferedOutputStream outputStream;

	while( fileItems.hasNext() )
	{
		FileItemStream fileItem = fileItems.next();
		String fieldName = fileItem.getFieldName();

		Boolean isSourcePic = fieldName.equals("__source");

		if ( fieldName.equals("__initParams") )
		{
			inputStream = new BufferedInputStream(fileItem.openStream());
			byte[] bytes = new byte [inputStream.available()];
			inputStream.read(bytes); 
			initParams = new String(bytes, "UTF-8");
			inputStream.close();
		}

		else if ( isSourcePic || fieldName.startsWith("__avatar") )
		{
			String virtualPath = "/headPortrait/jsp_avatar" + avatarNumber + "_" + fileName + ".jpg";

			if( isSourcePic )
			{

				String sourceFileName = fileItem.getName();	

				String sourceExtendName = sourceFileName.substring(sourceFileName.lastIndexOf('.') + 1);
				result.sourceUrl = virtualPath = String.format("/headPortrait/jsp_source_%s.%s", fileName, sourceExtendName);
			}

			else
			{
				result.avatarUrls.add(virtualPath);
				avatarNumber++;
			}
			inputStream = new BufferedInputStream(fileItem.openStream());
			outputStream = new BufferedOutputStream(new FileOutputStream(application.getRealPath("/") + virtualPath.replace("/", "\\")));
			Streams.copy(inputStream, outputStream, true);
			inputStream.close();
            outputStream.flush();
            outputStream.close();
		}
		else
		{

			inputStream = new BufferedInputStream(fileItem.openStream());
			byte[] bytes = new byte [inputStream.available()];
			inputStream.read(bytes); 
			inputStream.close();
			if (fieldName.equals("userid"))
			{
				result.userid = new String(bytes, "UTF-8");
			}
			else if (fieldName.equals("username"))
			{
				result.username = new String(bytes, "UTF-8");
			}
		}
	}



	if ( result.sourceUrl != null )
	{
		result.sourceUrl += initParams;
	}
	result.success = true;
	result.msg = "Success!";
	//Upload Avatar url in database
	UserBizImpl uBiz = new UserBizImpl();
	User u = uBiz.userFind(result.username);
	String avaUrl =(String)result.avatarUrls.get(0);
	u.setImage(avaUrl.substring(1, avaUrl.length() ) );
	
	//all methods in userbiz are same anyway ╮(╯▽╰)╭ 
	uBiz.userUpdateDescription(u);

	
	out.println(JSON.toJSONString(result));
}
%>
<%!


private class Result
{


	public Boolean success;
	public String userid;
	public String username;


	public String msg;


	public String sourceUrl;


	public ArrayList avatarUrls;
}
%>
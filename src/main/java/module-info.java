module fcaProduction {
	
	requires javafx.graphics;
	requires javafx.fxml;
//	requires fcaProduction;  dupa!

	requires javafx.controls;
	requires java.sql;
	requires poi;
	requires ormlite.core;
	requires ormlite.jdbc;
	
//	exports fcaProduction to javafx.graphics;
	exports fcaProduction;
	
}

//	exports fcaProduction.MainViewController to javafx.fxml;
//	 cannot access class fcaProduction.MainViewController (in module fcaProduction) because
//	module fcaProduction does not export fcaProduction to module javafx.fxml
		
//
///**
//Exception in Application start method
//Exception in thread "main" java.lang.RuntimeException: Exception in Application start method
//	at javafx.graphics/com.sun.javafx.application.LauncherImpl.launchApplication1(LauncherImpl.java:900)
//	at javafx.graphics/com.sun.javafx.application.LauncherImpl.lambda$launchApplication$2(LauncherImpl.java:195)
//	at java.base/java.lang.Thread.run(Thread.java:834)
//Caused by: javafx.fxml.LoadException:  @@
///C:/Users/user/eclipse-workspace/fcaProduction/target/classes/fcaProduction/mainView.fxml:7  @@
//
//	at javafx.fxml/javafx.fxml.FXMLLoader.constructLoadException(FXMLLoader.java:2625)
//	at javafx.fxml/javafx.fxml.FXMLLoader.access$700(FXMLLoader.java:105)
//	at javafx.fxml/javafx.fxml.FXMLLoader$ValueElement.processAttribute(FXMLLoader.java:943)
//	at javafx.fxml/javafx.fxml.FXMLLoader$InstanceDeclarationElement.processAttribute(FXMLLoader.java:980)
//	at javafx.fxml/javafx.fxml.FXMLLoader$Element.processStartElement(FXMLLoader.java:227)
//	at javafx.fxml/javafx.fxml.FXMLLoader$ValueElement.processStartElement(FXMLLoader.java:752)
//	at javafx.fxml/javafx.fxml.FXMLLoader.processStartElement(FXMLLoader.java:2722)
//	at javafx.fxml/javafx.fxml.FXMLLoader.loadImpl(FXMLLoader.java:2552)
//	at javafx.fxml/javafx.fxml.FXMLLoader.loadImpl(FXMLLoader.java:2466)
//	at javafx.fxml/javafx.fxml.FXMLLoader.loadImpl(FXMLLoader.java:3237)
//	at javafx.fxml/javafx.fxml.FXMLLoader.loadImpl(FXMLLoader.java:3194)
//	at javafx.fxml/javafx.fxml.FXMLLoader.loadImpl(FXMLLoader.java:3163)
//	at javafx.fxml/javafx.fxml.FXMLLoader.loadImpl(FXMLLoader.java:3136)
//	at javafx.fxml/javafx.fxml.FXMLLoader.loadImpl(FXMLLoader.java:3113)
//	at javafx.fxml/javafx.fxml.FXMLLoader.load(FXMLLoader.java:3106)
//	at fcaProduction/fcaProduction.MainClass.start(MainClass.java:15)
//	at javafx.graphics/com.sun.javafx.application.LauncherImpl.lambda$launchApplication1$9(LauncherImpl.java:846)
//	at javafx.graphics/com.sun.javafx.application.PlatformImpl.lambda$runAndWait$12(PlatformImpl.java:455)
//	at javafx.graphics/com.sun.javafx.application.PlatformImpl.lambda$runLater$10(PlatformImpl.java:428)
//	at java.base/java.security.AccessController.doPrivileged(Native Method)
//	at javafx.graphics/com.sun.javafx.application.PlatformImpl.lambda$runLater$11(PlatformImpl.java:427)
//	at javafx.graphics/com.sun.glass.ui.InvokeLaterDispatcher$Future.run(InvokeLaterDispatcher.java:96)
//	at javafx.graphics/com.sun.glass.ui.win.WinApplication._runLoop(Native Method)
//	at javafx.graphics/com.sun.glass.ui.win.WinApplication.lambda$runLoop$3(WinApplication.java:174)
//	... 1 more
//Caused by: java.lang.IllegalAccessException: class javafx.fxml.FXMLLoader$ValueElement (in module javafx.fxml) cannot access class fcaProduction.MainViewController (in module fcaProduction) because module fcaProduction does not export fcaProduction to module javafx.fxml
//	at java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:361)
//	at java.base/jdk.internal.reflect.Reflection.ensureMemberAccess(Reflection.java:99)
//	at java.base/java.lang.Class.newInstance(Class.java:579)
//	at javafx.fxml/javafx.fxml.FXMLLoader$ValueElement.processAttribute(FXMLLoader.java:936)
//	... 22 more
//
//
///**
 注意这章节最后 需要配置tomcat一个Connector 支持Https ,以及创建的 keystore文件需要放在tomcat的conf目录下
  
 <Connector SSLEnabled="true" keystoreFile="conf/tomcat.keystore" keystorePass="19940120" maxThreads="150" port="9443" protocol="HTTP/1.1" scheme="https" secure="true" sslProtocol="TLS"/>
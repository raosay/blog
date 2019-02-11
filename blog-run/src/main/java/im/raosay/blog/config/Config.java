package im.raosay.blog.config;

/**
 * @author ron
 * @version Config, v 0.1 2019/2/11 16:44 Administrator Exp $
 * @contact raosay92@gmail.com
 */
public class Config {


    public  static int WS_SERVER_PORT = Integer.parseInt(ConfigUtils.get("ws_server_port"));

    public  static int HEARTBEAT_TIMEOUT = Integer.parseInt(ConfigUtils.get("heartbeat_timeout"));

    public  static int HEARTBEAT_INTERVAL = Integer.parseInt(ConfigUtils.get("heartbeat_interval"));

}

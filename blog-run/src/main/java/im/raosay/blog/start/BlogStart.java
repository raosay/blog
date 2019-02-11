package im.raosay.blog.start;

import im.raosay.blog.config.Config;
import im.raosay.blog.socket.NettySocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ron
 * @version $Id: BlogStart, v 0.1 2019/2/11 15:48 Administrator Exp $
 */
public class BlogStart {

    private static final Logger logger = LoggerFactory.getLogger(BlogStart.class);


    public static void main(String[] args) throws Exception {


        NettySocketServer server = new NettySocketServer(Config.WS_SERVER_PORT);
        server.run();



    }
}

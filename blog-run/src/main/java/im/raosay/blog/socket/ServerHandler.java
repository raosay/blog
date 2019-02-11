package im.raosay.blog.socket;

import im.raosay.blog.codec.SocketRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ron
 * @version ServerHandler, v 0.1 2019/2/11 17:52 Administrator Exp $
 * @contact raosay92@gmail.com
 */
public class ServerHandler extends SimpleChannelInboundHandler<SocketRequest> {

    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端上线： ip = " + ctx.channel().remoteAddress());

        super.channelActive(ctx);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        logger.info("客户端下线： ip = " + ctx.channel().remoteAddress());

        super.channelInactive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketRequest msg) throws Exception {
        //
        logger.info("收到客户端请求消息： request = " + msg.toString());
    }
}

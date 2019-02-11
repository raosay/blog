package im.raosay.blog.socket;

import im.raosay.blog.codec.ServerDecoder;
import im.raosay.blog.codec.ServerEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @author ron
 * @version ServerChannelInitializer, v 0.1 2019/2/11 17:10 Administrator Exp $
 * @contact raosay92@gmail.com
 */
public class ServerChannelInitializer extends ChannelInitializer<Channel>{


    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast("encoder",new ServerEncoder());
        ch.pipeline().addLast("decoder",new ServerDecoder());
        ch.pipeline().addLast("handler",null);


    }
}

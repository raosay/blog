package im.raosay.blog.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ron
 * @version NettySocketServer, v 0.1 2019/2/11 16:48 Administrator Exp $
 * @contact raosay92@gmail.com
 */
public class NettySocketServer {


    private static final Logger logger = LoggerFactory.getLogger(NettySocketServer.class);

    private final int port ;

    public NettySocketServer(int port) {
        this.port = port;
    }

    public void run() throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true) //重用地址
                    .childOption(ChannelOption.SO_RCVBUF, 1048576)
                    .childOption(ChannelOption.SO_SNDBUF, 1048576)
                    .childOption(ChannelOption.ALLOCATOR, new PooledByteBufAllocator(false))  // heap buf 's better
                    .childHandler(new ServerChannelInitializer());
            logger.info("server socket starting ... ");
            ChannelFuture future = b.bind(port).sync();
            while (future.isSuccess()){
                logger.info("server socket started ... ");
                break;
            }


            future.channel().closeFuture().sync();
            logger.info("server socket shutdown ... ");


        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

}

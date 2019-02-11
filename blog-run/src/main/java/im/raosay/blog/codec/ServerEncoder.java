package im.raosay.blog.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author ron
 * @version ServerEncoder, v 0.1 2019/2/11 17:47 Administrator Exp $
 * @contact raosay92@gmail.com
 */
public class ServerEncoder extends MessageToByteEncoder<SocketResponse> {

    private static final Logger logger = LoggerFactory.getLogger(ServerEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, SocketResponse msg, ByteBuf out) throws Exception {


        // 回复指令：length(指令长度) + number(指令编号) + sequence(指令序号) + reserve(保留) + result(返回结果) + data(指令体)

        // 生成指令体
        ByteBuf dataBuffer = ProtoUtils.encode(msg.getValueMap());
        msg.setLength(dataBuffer.readableBytes() + 16);

        logger.debug("enter encode: " + msg.toString());

        // 输出指令头
        out.writeInt(msg.getLength());
        out.writeInt(msg.getNumber());
        out.writeInt(msg.getSequence());
        out.writeInt(msg.getReserve());
        out.writeInt(msg.getResult());

        // 输出指令体
        out.writeBytes(dataBuffer);
    }

}

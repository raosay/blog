package im.raosay.blog.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author ron
 * @version ServerDecoder, v 0.1 2019/2/11 17:14 Administrator Exp $
 * @contact raosay92@gmail.com
 */
public class ServerDecoder extends ByteToMessageDecoder{

    private static  final Logger logger = LoggerFactory.getLogger(ServerDecoder.class);

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 请求指令：length(指令长度) + number(指令编号) + sequence(指令序号) + reserve(保留) + sourceID(源角色ID) + targetID(对手角色ID) + data(指令体)

        logger.debug("enter decode: " + in.toString());

        if (in.readableBytes() < 24) {
            return; // 指令头不合法
        }

        in.markReaderIndex();

        int length = in.readInt();
        int number = in.readInt();
        int sequence = in.readInt();
        int reserve = in.readInt();
        int sourceID = in.readInt();
        int targetID = in.readInt();

        if (in.readableBytes() != (length - 20)) {
            in.resetReaderIndex();
            return; // 指令体长度不合法
        }

        ByteBuf dataBuffer = Unpooled.buffer(length - 20);
        in.readBytes(dataBuffer, length - 20);

        SocketRequest request = new SocketRequest();
        request.setLength(length);
        request.setNumber(number);
        request.setSequence(sequence);
        request.setReserve(reserve);
        request.setSourceID(sourceID);
        request.setTargetID(targetID);
        request.setParamMap(ProtoUtils.decode(dataBuffer));
        request.setIP(ProtoUtils.getClientIP(ctx.channel()));

        out.add(request);
    }
}

package im.raosay.blog.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;

import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ron
 * @version ProtoUtils, v 0.1 2019/2/11 17:23 Administrator Exp $
 * @contact raosay92@gmail.com
 */
public class ProtoUtils {


    public static ByteBuf encode(Map<String,String> encodeMap){
        if(encodeMap == null || encodeMap.isEmpty()){
            return null;
        }
        ByteBuf sum = Unpooled.buffer();
        Set<String> keys = encodeMap.keySet();
        keys.stream().forEach(s -> {
            ByteBuf b = Unpooled.buffer();
            b.writeInt(s.length());
            b.writeBytes(s.getBytes(CharsetUtil.UTF_8));

            String value =encodeMap.get(s);
            b.writeInt(value.length());
            b.writeBytes(value.getBytes(CharsetUtil.UTF_8));

            sum.writeBytes(b);

        });
        return sum;
    }



    public static Map<String,String> decode(ByteBuf byteBuf){
        Map<String,String> data = new HashMap<>();
        if(byteBuf == null || byteBuf.readableBytes() <= 0){
            return  data;
        }
        int index = 0;
        int length = byteBuf.readableBytes();
        while (index < length ){

            int size = byteBuf.readInt();

            byte[] content = new byte[size];
            byteBuf.readBytes(content);
            String key = new String(content,CharsetUtil.UTF_8);
            index +=size +4;


            size = byteBuf.readInt();

            content = new byte[size];
            byteBuf.readBytes(content);
            String value = new String(content,CharsetUtil.UTF_8);
            index +=size +4;

            data.put(key,value);

        }

        return data;
    }



    public static String getClientIP(Channel channel){
        SocketAddress address = channel.remoteAddress();
        String ip = "";

        if (address != null) {
            ip = address.toString().trim();
            int index = ip.lastIndexOf(':');

            if (index < 1) {
                index = ip.length();
            }
            ip = ip.substring(1, index);
        }

        if (ip.length() > 15) {
            ip = ip.substring(Math.max(ip.indexOf("/") + 1, ip.length() - 15));
        }

        return ip;
    }

}

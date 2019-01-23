package ${packagePath};

import com.imyeego.annotation.HandlerMapping;
import com.imyeego.protocol.request.${className};
import io.netty.channel.ChannelHandlerContext;

/**
 * @author liuzhao
 * @version 1.0
 * @since 2019/1/23
 */
@HandlerMapping("${className}")
public class ${className}Handler extends AbstractDataHandler<${className}> {
    @Override
    public void onEvent(${className} ${lowerClassName}, ChannelHandlerContext ctx) throws Exception {

    }
}
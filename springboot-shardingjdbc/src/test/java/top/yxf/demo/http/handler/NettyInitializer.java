package top.yxf.demo.http.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class NettyInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(Channel ch) throws Exception {


        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new NettyHandler());

    }

}

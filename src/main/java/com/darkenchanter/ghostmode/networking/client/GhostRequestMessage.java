package com.darkenchanter.ghostmode.networking.client;

import io.netty.buffer.ByteBuf;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;

public class GhostRequestMessage implements IMessage {
	private String entityPlayer;
	public String getEntityPlayer() {
		return entityPlayer;
	}
	public GhostRequestMessage() {
		entityPlayer = "";
	}
	public GhostRequestMessage(String player) {
		entityPlayer = player;
	}
	@Override
	public void fromBytes (ByteBuf buf) {
		String decode = "";
		try {
			decode = CharsetUtil.decoder(CharsetUtil.UTF_16).decode(buf.nioBuffer()).toString();
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		entityPlayer = decode;
	}

	@Override
	public void toBytes (ByteBuf buf) {
		try {
			buf.writeBytes(CharsetUtil.encoder(CharsetUtil.UTF_16).encode(CharBuffer.wrap(entityPlayer)));
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
	}
}

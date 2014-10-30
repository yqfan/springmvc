package com.yqfan.simplemvc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import come.yqfan.simplemvc.model.Gift;

public class GiftFileManager {
	public static GiftFileManager get() throws IOException {
		return new GiftFileManager();
	}
	
	private Path storageDir = Paths.get("gifts");
	
	private GiftFileManager() throws IOException{
		if (!Files.exists(storageDir)) {
			Files.createDirectories(storageDir);
		}
	}
	
	private Path getGiftPath(Gift g) {
		assert(g != null);
		return storageDir.resolve(g.getDataUrl());
	}
	
	public boolean hasGiftData(Gift g) {
		Path source = getGiftPath(g);
		return Files.exists(source);
	}
	
	public void copyGiftFile(Gift g, OutputStream out) throws IOException {
		Path source = getGiftPath(g);
		if (!Files.exists(source)) {
			throw new FileNotFoundException();
		}
		Files.copy(source, out);
	}
	
	public void saveGiftFile(Gift g, InputStream inData) throws IOException {
		assert(inData != null);
		
		Path target = getGiftPath(g);
		Files.copy(inData, target, StandardCopyOption.REPLACE_EXISTING);
	}
}

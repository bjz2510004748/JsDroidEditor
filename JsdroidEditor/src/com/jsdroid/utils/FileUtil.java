package com.jsdroid.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class FileUtil {
	public static byte[] readBytes(String filename) {
		File file = new File(filename);
		return readBytes(file);
	}

	public static void writeBytes(String filename, byte[] data) {
		File file = new File(filename);
		writeBytes(file, data);
	}

	public static void writeBytes(File file, byte[] data) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static byte[] readBytes(File file) {
		if (!file.exists()) {
			return null;
		}
		FileInputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			in = new FileInputStream(file);
			cpyStream(in, out);
		} catch (Exception e) {
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return out.toByteArray();

	}

	public static byte[] readBytes(InputStream in) {
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			cpyStream(in, out);
		} catch (Exception e) {
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return out.toByteArray();

	}

	public static void append(String file, String content) {
		try {
			RandomAccessFile rf = new RandomAccessFile(file, "rw");
			rf.seek(rf.length()); // ��ָ���ƶ����ļ�ĩβ
			rf.write((content + "\n").getBytes("utf-8"));
			rf.close();// �ر��ļ���
		} catch (Exception e) {
		}
	}

	public static void cpyStream(InputStream in, OutputStream out)
			throws IOException {
		byte buff[] = new byte[1024];
		int len = 0;
		while ((len = in.read(buff)) != -1) {
			out.write(buff, 0, len);
		}
		out.close();
		in.close();
	}

	public static void createDir(String filename) {
		File file = new File(filename).getAbsoluteFile();
		if(file.isDirectory()){
			file.mkdirs();
			return;
		}
		file.getParentFile().mkdirs();
	}

	public static String read(File file) {
		if (!file.exists()) {
			return null;
		}
		try {
			return read(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String read(InputStream in) {
		BufferedReader reader = null;
		StringBuffer result = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String line = null;
			result = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				result.append(line);
				result.append("\n");
			}
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return null;
	}

	public static String read(String filename) {
		return read(new File(filename));
	}


	public static void write(String filename, String content) {
		createDir(filename);
		write(new File(filename), content);

	}

	public static void write(File file, String content) {
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(content.getBytes("utf-8"));
		} catch (Exception e) {
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	}

}

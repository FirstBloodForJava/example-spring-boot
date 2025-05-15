package com.example.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author ouyangcm
 * create 2025/5/15 14:28
 */
public class ConfigLoad {

    /**
     * 保存 config 配置至文件
     *
     * @param appName
     * @param group
     * @param type
     * @param config
     * @throws IOException
     */
    public static void save(String appName, String group, String type, String config) throws IOException {
        createFile(appName, group, type);
        FileOutputStream fileOutputStream = new FileOutputStream(getLocalConfigFile(appName, group, type));
        IOException ioException = null;

        try {
            fileOutputStream.write(config.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            ioException = e;
            throw e;
        } finally {

            if (ioException != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    ioException.addSuppressed(e);
                }
            } else {
                fileOutputStream.close();
            }


        }

    }

    /**
     * 加载本地配置文件
     * @param appName
     * @param group
     * @param type
     * @return
     * @throws IOException
     */
    public static Optional<String> load(String appName, String group, String type) throws IOException {
        File file = new File(getLocalConfigFile(appName, group, type));
        if (!file.exists()) {
            return Optional.empty();
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            IOException ioException = null;

            try {
                StringBuilder sb = new StringBuilder();

                String tmp;
                while ((tmp = reader.readLine()) != null) {
                    sb.append(tmp);
                }

                return Optional.of(sb.toString());
            } catch (IOException exception) {
                ioException = exception;
                throw exception;
            } finally {

                if (ioException != null) {
                    try {
                        reader.close();
                    } catch (IOException exception) {
                        ioException.addSuppressed(exception);
                    }
                } else {
                    reader.close();
                }


            }
        }
    }


    /**
     * 创建要保存配置的文件
     *
     * @param appName 应用名称
     * @param group   配置组
     * @param type    配置类型
     * @return 是否保存成功
     * @throws IOException
     */
    private static boolean createFile(String appName, String group, String type) throws IOException {
        File file = new File(getLocalConfigFile(appName, group, type));
        if (file.exists()) {
            return true;
        } else {
            File dictionary = new File(getLocalConfigDictionary(appName, group, type));
            dictionary.mkdirs();
            return file.createNewFile();
        }
    }

    /**
     * 获取保存的文件名 全路径
     *
     * @param appName 应用名
     * @param group   配置组
     * @param type    配置类型
     * @return ./config/[type]/[appName]/[group]/configuration
     */
    private static String getLocalConfigFile(String appName, String group, String type) {
        return String.join(File.separator, ".", "config", type, appName, group, "configuration");
    }

    /**
     * 获取文件的路径
     *
     * @param appName
     * @param group
     * @param type
     * @return
     */
    private static String getLocalConfigDictionary(String appName, String group, String type) {
        return String.join(File.separator, ".", "config", type, appName, group);
    }
}

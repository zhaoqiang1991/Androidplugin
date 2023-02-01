package com.hm.iou.lifecycle.plugin;

import groovy.xml.MarkupBuilder
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.io.StringWriter;


/**
 * 自定义task，实现维护版本信息功能
 */
class ReleaseInfoTask extends DefaultTask {

    ReleaseInfoTask() {
        group = 'lh'
        description = 'update the release info'
    }

    /**
     * 执行于gradle执行阶段的代码
     */
    @TaskAction
    void doAction() {
        updateInfo()
    }

    //真正的将Extension类中的信息呢，写入指定文件中
    private void updateInfo() {
        //获取将要写入的信息
        String versionNameMsg = project.extensions.lhReleaseInfo.versionName
        String versionCodeMsg = project.extensions.lhReleaseInfo.versionCode
        String versionInfoMsg = project.extensions.lhReleaseInfo.versionInfo
        String fileNameMsg = project.extensions.lhReleaseInfo.fileName
        def file = project.file(fileNameMsg)
        //将实体对象转化为xml格式数据
        def sw = new StringWriter()
        def xmlBuilder = new MarkupBuilder(sw)
        if (file.text != null && file.text.size() <= 0) {
            //文件中没有内容
            xmlBuilder.releases {
                release {
                    versionName(versionNameMsg)
                    versionCode(versionCodeMsg)
                    versionInfo(versionInfoMsg)
                }
            }
            file.withWriter { writer ->
                writer.append(sw.toString())
            }
        } else {
            //文件中已有内容
            xmlBuilder.release {
                versionName(versionNameMsg)
                versionCode(versionCodeMsg)
                versionInfo(versionInfoMsg)
            }
            //将生成的xml数据插入到根结点之前
            def lines = file.readLines()
            def lengths = lines.size() - 1
            file.withWriter { writer ->
                lines.eachWithIndex{  String line, int index ->
                    if (index != lengths) {
                        writer.append(line + '\n')
                    } else if (index == lengths) {
                        writer.append('\t\n' + sw.toString() + '\n')
                        writer.append(lines.get(lengths))
                    }
                }
            }
        }


    }

}


//package com.yizhu.lj.pachong;
//
//import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.Site;
//import us.codecraft.webmagic.Spider;
//import us.codecraft.webmagic.processor.PageProcessor;
//
//import java.util.List;
//
///**
// * 功能描述
// *
// * @author Lj
// * @date 2020/3/16
// */
//
//
//class CsdnBlogSplider implements PageProcessor {
//
//    private Site site = Site
//            .me()
//            .setDomain("https://blog.csdn.net")
//            .setSleepTime(3000)
//            .setUserAgent(
//                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36");
//
//    public void process(Page page) {
//
//        //具体博客页面解析
//        if (page.getUrl().get().startsWith("https://blog.csdn.net/caihaijiang/article/details")) {
//
//            //这里进行标签解析，根据你需要的字段的位置进行标签定位
//            String subject = page.getHtml().xpath("//h6[@class='title-article']").get().replace("<h6 class=\"title-article\">", "").replace("</h6>", "");
//            String time = page.getHtml().xpath("//div[@class='article-bar-top d-flex']/span").replace("<span class=\"time\">", "").replace("</span>", "").toString();
//            String readNum = page.getHtml().xpath("//div[@class='article-bar-top d-flex']/div[1]/span").replace("<span class=\"read-count\">", "").replace("</span>", "").toString();
//
//            System.out.println(subject);
//            System.out.println(time);
//            System.out.println(readNum);
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException in) {
//                System.out.println("线程出错···");
//            }
//        } else {
//            //列表页面解析,当前页所有博客url添加进解析列表
//            page.addTargetRequests(page.getHtml().xpath("//div[@class='article-item-box csdn-tracking-statistics']/h4/a").links().all());
//        }
//    }
//
//    public Site getSite() {
//        return site;
//    }
//
//    public static void main(String[] args) {
//
//        Spider spider = Spider.create(new CsdnBlogSplider());
//        //共7页，添加7次列表页url
//        for (int i = 1; i < 8; i++) {
//            spider.addUrl("https://blog.csdn.net/caihaijiang/article/list/" + i + "?");
//        }
//        spider.run();
//    }
//}
//
//
//
//
//

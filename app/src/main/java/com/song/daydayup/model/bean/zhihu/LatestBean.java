package com.song.daydayup.model.bean.zhihu;

import java.util.List;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 * 知乎日报最新消息
 */

public class LatestBean {

    /**
     * date : 20170401
     * top_stories : [{"image":"https://pic3.zhimg.com/v2-44cb4e36024c52a25168c037f07c7dde.jpg","ga_prefix":"040113","id":9330162,"title":"60 岁的张国荣：遗书、情书和书单","type":0},{"image":"https://pic2.zhimg.com/v2-18af4261e5301589895a19886a18fb35.jpg","ga_prefix":"040107","id":9327876,"title":"腾讯入股特斯拉，抢在百度和阿里前面进入了汽车市场","type":0},{"image":"https://pic4.zhimg.com/v2-c2accd741b19bc20e61e2d9d1dcf5317.jpg","ga_prefix":"040107","id":9328292,"title":"幼童撞翻铁锅被烫伤，饭店有责任吗？索赔 39 万合理吗？","type":0},{"image":"https://pic3.zhimg.com/v2-4d87ae93164368090397d54b07b4e366.jpg","ga_prefix":"033120","id":9328977,"title":"SpaceX 又创下了里程碑，今天开始运载火箭能重复使用","type":0},{"image":"https://pic3.zhimg.com/v2-b4aeee769480780b1e0913fc639f680e.jpg","ga_prefix":"033114","id":9328195,"title":"朴槿惠被批捕，成为韩国史上第 3 位被收押的卸任元首","type":0}]
     * stories : [{"images":["https://pic3.zhimg.com/v2-a38460d8eb5b2a38d5fe8ead5eef3286.jpg"],"ga_prefix":"040113","id":9330162,"title":"60 岁的张国荣：遗书、情书和书单","type":0},{"images":["https://pic1.zhimg.com/v2-b6377c033a5be6d04e3dfd6a902352e4.jpg"],"ga_prefix":"040113","id":9322361,"title":"原料少好上手，最适合放假自己动手做的一款饼干","type":0},{"images":["https://pic3.zhimg.com/v2-cc5b2b39710f695a304259c48b53830a.jpg"],"ga_prefix":"040112","id":9329932,"title":"大误 · 和（hàn）你讲喔，我手机会飞嘞","type":0},{"images":["https://pic1.zhimg.com/v2-6197f5bfc6f25df3fe2bdbc4e41a540c.jpg"],"ga_prefix":"040110","multipic":true,"id":9329525,"title":"让机器人去冰箱里拿瓶可乐有多难？","type":0},{"images":["https://pic2.zhimg.com/v2-8e4dc805e7d67c3e4b8a77bb02c84cb9.jpg"],"ga_prefix":"040109","id":9328164,"title":"专门生产手机后壳，这家公司股价 10 年涨了 70 倍，净利 9 亿港币","type":0},{"images":["https://pic1.zhimg.com/v2-b6f9dcada477e4159c3d3b0672269ba8.jpg"],"ga_prefix":"040108","multipic":true,"id":9329498,"title":"如果觉得鱼塘太小，让我们出发去大海上钓鱼吧","type":0},{"images":["https://pic1.zhimg.com/v2-f72afcc50418a6b927f24bb7b684d31c.jpg"],"ga_prefix":"040107","id":9325892,"title":"听我一句劝，这些说自己能缩毛孔的产品都最好别用","type":0},{"images":["https://pic3.zhimg.com/v2-c5ba26325190fd7dc6f391fd02ccb10e.jpg"],"ga_prefix":"040107","id":9328292,"title":"幼童撞翻铁锅被烫伤，饭店有责任吗？索赔 39 万合理吗？","type":0},{"images":["https://pic2.zhimg.com/v2-841902cb9bef33d2c958c5bcb352e7f5.jpg"],"ga_prefix":"040107","id":9327876,"title":"腾讯入股特斯拉，抢在百度和阿里前面进入了汽车市场","type":0},{"images":["https://pic1.zhimg.com/v2-8b4d89bafe6e9a323d7bc57985a823cc.jpg"],"ga_prefix":"040106","id":9325407,"title":"瞎扯 · 如何正确地吐槽","type":0}]
     */
    private String date;
    private List<TopStoriesEntity> top_stories;
    private List<StoriesEntity> stories;


    public String getDate() {
        return date;
    }

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public static class TopStoriesEntity {
        /**
         * image : https://pic3.zhimg.com/v2-44cb4e36024c52a25168c037f07c7dde.jpg
         * ga_prefix : 040113
         * id : 9330162
         * title : 60 岁的张国荣：遗书、情书和书单
         * type : 0
         */
        private String image;
        private String ga_prefix;
        private int id;
        private String title;
        private int type;

        public String getImage() {
            return image;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }
    }

    public static class StoriesEntity {
        /**
         * images : ["https://pic3.zhimg.com/v2-a38460d8eb5b2a38d5fe8ead5eef3286.jpg"]
         * ga_prefix : 040113
         * id : 9330162
         * title : 60 岁的张国荣：遗书、情书和书单
         * type : 0
         */
        private List<String> images;
        private String ga_prefix;
        private int id;
        private String title;
        private int type;


        public List<String> getImages() {
            return images;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }
    }
}

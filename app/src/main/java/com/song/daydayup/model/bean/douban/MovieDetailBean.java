package com.song.daydayup.model.bean.douban;

import java.util.List;

/**
 * Created by Chen.Qingsong on 2017/3/1.
 */
public class MovieDetailBean {
    /**
     * summary : 克里斯•加纳（威尔·史密斯 Will Smith 饰）用尽全部积蓄买下了高科技治疗仪，到处向医院推销，可是价格高昂，接受的人不多。就算他多努力都无法提供一个良好的生活环境给妻儿，妻子（桑迪·牛顿 Thandie Newton 饰）最终选择离开家。从此他带着儿子克里斯托夫（贾登·史密斯 Jaden Smith 饰）相依为命。克里斯好不容易争取回来一个股票投资公司实习的机会，就算没有报酬，成功机会只有百分之五，他仍努力奋斗，儿子是他的力量。他看尽白眼，与儿子躲在地铁站里的公共厕所里，住在教堂的收容所里…… 他坚信，幸福明天就会来临。©豆瓣
     * schedule_url :
     * comments_count : 99286
     * aka : ["寻找快乐的故事(港)","追求快乐","幸福追击"]
     * genres : ["剧情","传记","家庭"]
     * ratings_count : 502065
     * countries : ["美国"]
     * id : 1849031
     * title : 当幸福来敲门
     * share_url : https://m.douban.com/movie/subject/1849031
     * alt : https://movie.douban.com/subject/1849031/
     * subtype : movie
     * original_title : The Pursuit of Happyness
     * mobile_url : https://movie.douban.com/subject/1849031/mobile
     * wish_count : 52460
     * reviews_count : 2550
     * images : {"small":"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p1312700628.jpg","medium":"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p1312700628.jpg","large":"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p1312700628.jpg"}
     * year : 2006
     * casts : [{"id":"1027138","alt":"https://movie.douban.com/celebrity/1027138/","name":"威尔·史密斯","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/31885.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/31885.jpg","large":"https://img3.doubanio.com/img/celebrity/large/31885.jpg"}},{"id":"1010532","alt":"https://movie.douban.com/celebrity/1010532/","name":"贾登·史密斯","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/1374248074.98.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1374248074.98.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1374248074.98.jpg"}},{"id":"1040513","alt":"https://movie.douban.com/celebrity/1040513/","name":"坦迪·牛顿","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/1378018910.89.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1378018910.89.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1378018910.89.jpg"}},{"id":"1317100","alt":"https://movie.douban.com/celebrity/1317100/","name":"布莱恩·豪威 ","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/38587.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/38587.jpg","large":"https://img1.doubanio.com/img/celebrity/large/38587.jpg"}}]
     * rating : {"min":0,"max":10,"stars":"45","average":8.9}
     * collect_count : 668824
     * directors : [{"id":"1045093","alt":"https://movie.douban.com/celebrity/1045093/","name":"加布里尔·穆奇诺","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/20409.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/20409.jpg","large":"https://img1.doubanio.com/img/celebrity/large/20409.jpg"}}]
     * douban_site :
     */
    private String summary;
    private String schedule_url;
    private int comments_count;
    private List<String> aka;
    private List<String> genres;
    private int ratings_count;
    private List<String> countries;
    private String id;
    private String title;
    private String share_url;
    private String alt;
    private String subtype;
    private String original_title;
    private String mobile_url;
    private int wish_count;
    private int reviews_count;
    private ImagesEntity images;
    private String year;
    private List<CastsEntity> casts;
    private RatingEntity rating;
    private int collect_count;
    private List<DirectorsEntity> directors;
    private String douban_site;

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public void setImages(ImagesEntity images) {
        this.images = images;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCasts(List<CastsEntity> casts) {
        this.casts = casts;
    }

    public void setRating(RatingEntity rating) {
        this.rating = rating;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public void setDirectors(List<DirectorsEntity> directors) {
        this.directors = directors;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getSummary() {
        return summary;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public int getComments_count() {
        return comments_count;
    }

    public List<String> getAka() {
        return aka;
    }

    public List<String> getGenres() {
        return genres;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getAlt() {
        return alt;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public int getWish_count() {
        return wish_count;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public ImagesEntity getImages() {
        return images;
    }

    public String getYear() {
        return year;
    }

    public List<CastsEntity> getCasts() {
        return casts;
    }

    public RatingEntity getRating() {
        return rating;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public List<DirectorsEntity> getDirectors() {
        return directors;
    }

    public String getDouban_site() {
        return douban_site;
    }

    @Override
    public String toString() {
        return "MovieDetailBean{" +
                "summary='" + summary + '\'' +
                ", schedule_url='" + schedule_url + '\'' +
                ", comments_count=" + comments_count +
                ", aka=" + aka +
                ", genres=" + genres +
                ", ratings_count=" + ratings_count +
                ", countries=" + countries +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", share_url='" + share_url + '\'' +
                ", alt='" + alt + '\'' +
                ", subtype='" + subtype + '\'' +
                ", original_title='" + original_title + '\'' +
                ", mobile_url='" + mobile_url + '\'' +
                ", wish_count=" + wish_count +
                ", reviews_count=" + reviews_count +
                ", images=" + images +
                ", year='" + year + '\'' +
                ", casts=" + casts +
                ", rating=" + rating +
                ", collect_count=" + collect_count +
                ", directors=" + directors +
                ", douban_site='" + douban_site + '\'' +
                '}';
    }

    public static class ImagesEntity {
        /**
         * small : https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p1312700628.jpg
         * medium : https://img1.doubanio.com/view/movie_poster_cover/spst/public/p1312700628.jpg
         * large : https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p1312700628.jpg
         */
        private String small;
        private String medium;
        private String large;

        public void setSmall(String small) {
            this.small = small;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getSmall() {
            return small;
        }

        public String getMedium() {
            return medium;
        }

        public String getLarge() {
            return large;
        }
    }

    public static class CastsEntity {
        /**
         * id : 1027138
         * alt : https://movie.douban.com/celebrity/1027138/
         * name : 威尔·史密斯
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/31885.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/31885.jpg","large":"https://img3.doubanio.com/img/celebrity/large/31885.jpg"}
         */
        private String id;
        private String alt;
        private String name;
        private AvatarsEntity avatars;

        public void setId(String id) {
            this.id = id;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAvatars(AvatarsEntity avatars) {
            this.avatars = avatars;
        }

        public String getId() {
            return id;
        }

        public String getAlt() {
            return alt;
        }

        public String getName() {
            return name;
        }

        public AvatarsEntity getAvatars() {
            return avatars;
        }

        public static class AvatarsEntity {
            /**
             * small : https://img3.doubanio.com/img/celebrity/small/31885.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/31885.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/31885.jpg
             */
            private String small;
            private String medium;
            private String large;

            public void setSmall(String small) {
                this.small = small;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getSmall() {
                return small;
            }

            public String getMedium() {
                return medium;
            }

            public String getLarge() {
                return large;
            }
        }
    }

    public static class RatingEntity {
        /**
         * min : 0
         * max : 10
         * stars : 45
         * average : 8.9
         */
        private int min;
        private int max;
        private String stars;
        private double average;

        public void setMin(int min) {
            this.min = min;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        public String getStars() {
            return stars;
        }

        public double getAverage() {
            return average;
        }
    }

    public static class DirectorsEntity {
        /**
         * id : 1045093
         * alt : https://movie.douban.com/celebrity/1045093/
         * name : 加布里尔·穆奇诺
         * avatars : {"small":"https://img1.doubanio.com/img/celebrity/small/20409.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/20409.jpg","large":"https://img1.doubanio.com/img/celebrity/large/20409.jpg"}
         */
        private String id;
        private String alt;
        private String name;
        private AvatarsEntity avatars;

        public void setId(String id) {
            this.id = id;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAvatars(AvatarsEntity avatars) {
            this.avatars = avatars;
        }

        public String getId() {
            return id;
        }

        public String getAlt() {
            return alt;
        }

        public String getName() {
            return name;
        }

        public AvatarsEntity getAvatars() {
            return avatars;
        }

        public static class AvatarsEntity {
            /**
             * small : https://img1.doubanio.com/img/celebrity/small/20409.jpg
             * medium : https://img1.doubanio.com/img/celebrity/medium/20409.jpg
             * large : https://img1.doubanio.com/img/celebrity/large/20409.jpg
             */
            private String small;
            private String medium;
            private String large;

            public void setSmall(String small) {
                this.small = small;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getSmall() {
                return small;
            }

            public String getMedium() {
                return medium;
            }

            public String getLarge() {
                return large;
            }
        }
    }
}

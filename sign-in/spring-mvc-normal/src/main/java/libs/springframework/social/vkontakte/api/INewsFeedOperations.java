package libs.springframework.social.vkontakte.api;

import libs.springframework.social.vkontakte.api.impl.NewsFeedSearchRequest;

/**
 * Defines operations for interacting with a user's newsfeed.
 * @author dIsoVi
 */
public interface INewsFeedOperations {
    NewsFeedResponse searchNews(NewsFeedSearchRequest request);
}

package com.example.desafio_android_accenture.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryModel(
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("description") val description: String,
    @SerializedName("html_url") val repo_url: String,
    @SerializedName("open_issues") val issuesOpened: String,
    @SerializedName("stargazers_count") val stars: String,
    @SerializedName("forks_count") val branches: String,
    @SerializedName("user_img_url") val userImgUrl: String?,
    @SerializedName("owner") val user: UserModel
) {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as RepositoryModel
        if (name != other.name) return false
        if (fullName != other.fullName) return false
        if (description != other.description) return false
        if (repo_url != other.repo_url) return false
        if (issuesOpened != other.issuesOpened) return false
        if (stars != other.stars) return false
        if (branches != other.branches) return false
        if (userImgUrl != other.userImgUrl) return false
        if (user != other.user) return false
        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + fullName.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + repo_url.hashCode()
        result = 31 * result + issuesOpened.hashCode()
        result = 31 * result + stars.hashCode()
        result = 31 * result + branches.hashCode()
        result = 31 * result + userImgUrl.hashCode()
        result = 31 * result + user.hashCode()
        return result
    }

}

/*
{
    "id":132464395,
    "name":"JavaGuide",
    "full_name":"Snailclimb/JavaGuide",
    "private":false,
    "html_url":"https://github.com/Snailclimb/JavaGuide",
    "description":"「Java学习+面试指南」一份涵盖大部分 Java 程序员所需要掌握的核心知识。准备 Java 面试，首选 JavaGuide！",
    "fork":false,
    "url":"https://api.github.com/repos/Snailclimb/JavaGuide",
    "forks_url":"https://api.github.com/repos/Snailclimb/JavaGuide/forks",
    "keys_url":"https://api.github.com/repos/Snailclimb/JavaGuide/keys{/key_id}",
    "collaborators_url":"https://api.github.com/repos/Snailclimb/JavaGuide/collaborators{/collaborator}",
    "teams_url":"https://api.github.com/repos/Snailclimb/JavaGuide/teams",
    "hooks_url":"https://api.github.com/repos/Snailclimb/JavaGuide/hooks",
    "issue_events_url":"https://api.github.com/repos/Snailclimb/JavaGuide/issues/events{/number}",
    "events_url":"https://api.github.com/repos/Snailclimb/JavaGuide/events",
    "assignees_url":"https://api.github.com/repos/Snailclimb/JavaGuide/assignees{/user}",
    "branches_url":"https://api.github.com/repos/Snailclimb/JavaGuide/branches{/branch}",
    "tags_url":"https://api.github.com/repos/Snailclimb/JavaGuide/tags",
    "blobs_url":"https://api.github.com/repos/Snailclimb/JavaGuide/git/blobs{/sha}",
    "git_tags_url":"https://api.github.com/repos/Snailclimb/JavaGuide/git/tags{/sha}",
    "git_refs_url":"https://api.github.com/repos/Snailclimb/JavaGuide/git/refs{/sha}",
    "trees_url":"https://api.github.com/repos/Snailclimb/JavaGuide/git/trees{/sha}",
    "statuses_url":"https://api.github.com/repos/Snailclimb/JavaGuide/statuses/{sha}",
    "languages_url":"https://api.github.com/repos/Snailclimb/JavaGuide/languages",
    "stargazers_url":"https://api.github.com/repos/Snailclimb/JavaGuide/stargazers",
    "contributors_url":"https://api.github.com/repos/Snailclimb/JavaGuide/contributors",
    "subscribers_url":"https://api.github.com/repos/Snailclimb/JavaGuide/subscribers",
    "subscription_url":"https://api.github.com/repos/Snailclimb/JavaGuide/subscription",
    "commits_url":"https://api.github.com/repos/Snailclimb/JavaGuide/commits{/sha}",
    "git_commits_url":"https://api.github.com/repos/Snailclimb/JavaGuide/git/commits{/sha}",
    "comments_url":"https://api.github.com/repos/Snailclimb/JavaGuide/comments{/number}",
    "issue_comment_url":"https://api.github.com/repos/Snailclimb/JavaGuide/issues/comments{/number}",
    "contents_url":"https://api.github.com/repos/Snailclimb/JavaGuide/contents/{+path}",
    "compare_url":"https://api.github.com/repos/Snailclimb/JavaGuide/compare/{base}...{head}",
    "merges_url":"https://api.github.com/repos/Snailclimb/JavaGuide/merges",
    "archive_url":"https://api.github.com/repos/Snailclimb/JavaGuide/{archive_format}{/ref}",
    "downloads_url":"https://api.github.com/repos/Snailclimb/JavaGuide/downloads",
    "issues_url":"https://api.github.com/repos/Snailclimb/JavaGuide/issues{/number}",
    "pulls_url":"https://api.github.com/repos/Snailclimb/JavaGuide/pulls{/number}",
    "milestones_url":"https://api.github.com/repos/Snailclimb/JavaGuide/milestones{/number}",
    "notifications_url":"https://api.github.com/repos/Snailclimb/JavaGuide/notifications{?since,all,participating}",
    "labels_url":"https://api.github.com/repos/Snailclimb/JavaGuide/labels{/name}",
    "releases_url":"https://api.github.com/repos/Snailclimb/JavaGuide/releases{/id}",
    "deployments_url":"https://api.github.com/repos/Snailclimb/JavaGuide/deployments",
    "created_at":"2018-05-07T13:27:00Z",
    "updated_at":"2022-03-30T19:00:47Z",
    "pushed_at":"2022-03-30T12:32:52Z",
    "git_url":"git://github.com/Snailclimb/JavaGuide.git",
    "ssh_url":"git@github.com:Snailclimb/JavaGuide.git",
    "clone_url":"https://github.com/Snailclimb/JavaGuide.git",
    "svn_url":"https://github.com/Snailclimb/JavaGuide",
    "homepage":"https://javaguide.cn",
    "size":149707,
    "stargazers_count":119520,
    "watchers_count":119520,
    "language":"Java",
    "has_issues":true,
    "has_projects":true,
    "has_downloads":true,
    "has_wiki":true,
    "has_pages":false,
    "forks_count":40689,
    "mirror_url":null,
    "archived":false,
    "disabled":false,
    "open_issues_count":72,
    "license":null,
    "allow_forking":true,
    "is_template":false,
    "topics":[
    "algorithms",
    "interview",
    "java",
    "jvm",
    "mysql",
    "redis",
    "spring",
    "system",
    "system-design",
    "zookeeper"
    ],
    "visibility":"public",
    "forks":40689,
    "open_issues":72,
    "watchers":119520,
    "default_branch":"main",
    "permissions":{
    "admin":false,
    "maintain":false,
    "push":false,
    "triage":false,
    "pull":true
},
    "score":1.0
}
*/

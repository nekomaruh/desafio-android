package com.example.desafio_android_accenture.data.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("id") val id:Int,
    @SerializedName("login") val login:String,
    @SerializedName("avatar_url") val avatarUrl:String,
    @SerializedName("url") val url:String,
)

/*
"owner":{
            "login":"Snailclimb",
            "id":29880145,
            "node_id":"MDQ6VXNlcjI5ODgwMTQ1",
            "avatar_url":"https://avatars.githubusercontent.com/u/29880145?v=4",
            "gravatar_id":"",
            "url":"https://api.github.com/users/Snailclimb",
            "html_url":"https://github.com/Snailclimb",
            "followers_url":"https://api.github.com/users/Snailclimb/followers",
            "following_url":"https://api.github.com/users/Snailclimb/following{/other_user}",
            "gists_url":"https://api.github.com/users/Snailclimb/gists{/gist_id}",
            "starred_url":"https://api.github.com/users/Snailclimb/starred{/owner}{/repo}",
            "subscriptions_url":"https://api.github.com/users/Snailclimb/subscriptions",
            "organizations_url":"https://api.github.com/users/Snailclimb/orgs",
            "repos_url":"https://api.github.com/users/Snailclimb/repos",
            "events_url":"https://api.github.com/users/Snailclimb/events{/privacy}",
            "received_events_url":"https://api.github.com/users/Snailclimb/received_events",
            "type":"User",
            "site_admin":false
         },
 */
package app.Project_setup.data.local

import app.Project_setup.data.local.entities.EntityUsers
import app.Project_setup.data.remote.data.User

fun User.getEntity(): EntityUsers {
    return EntityUsers(
        this.id,
        this.name,
        this.username,
        this.email,
        this.bio,
        this.profile,
        this.followers,
        this.following,
        this.postCount
    )
}

package xyz.arifz.dfatest.domain.mapper

import xyz.arifz.dfatest.data.local.models.PostEntity
import xyz.arifz.dfatest.data.local.models.PostResponse

object PostMapper {

    fun mapToEntity(postResponse: PostResponse): PostEntity {
        return PostEntity(
            id = postResponse.id,
            userId = postResponse.userId,
            title = postResponse.title,
            body = postResponse.body
        )
    }

    fun mapToEntityList(postResponses: List<PostResponse>): List<PostEntity> {
        return postResponses.map { mapToEntity(it) }
    }
}

package com.bankuish.challenge.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bankuish.challenge.data.datasource.api.repositories.RepositoriesApiDataSource
import com.bankuish.challenge.data.datasource.api.repositories.entity.toRepositories
import com.bankuish.challenge.domain.model.Repositories
import retrofit2.HttpException
import java.io.IOException

class RepositoriesPagingSource(private val repository: RepositoriesApiDataSource) :
    PagingSource<Int, Repositories>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repositories> {
        val page = params.key ?: 1

        return try {
            val response = repository.getRepositories("kotlin", perPage = params.loadSize,page= page)
            val nextKey = if (response.items.size<params.loadSize) null else page.plus(1)
            val prevKey = if(page==1)null else page.minus(1)

            LoadResult.Page(
                data = response.items.map { it.toRepositories() },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            Log.d("PAGING",e.toString())
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.d("PAGING",e.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repositories>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}
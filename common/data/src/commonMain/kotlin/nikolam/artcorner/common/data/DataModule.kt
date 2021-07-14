package nikolam.artcorner.common.data

import org.koin.dsl.module

fun dataModule () = module {
    single{GroupApi(get())}
}
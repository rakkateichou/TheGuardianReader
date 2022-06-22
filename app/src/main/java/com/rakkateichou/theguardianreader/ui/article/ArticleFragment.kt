package com.rakkateichou.theguardianreader.ui.article

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rakkateichou.theguardianreader.R
import com.rakkateichou.theguardianreader.data.model.Body
import com.rakkateichou.theguardianreader.data.model.CardType
import com.rakkateichou.theguardianreader.data.model.NewsEntry
import com.rakkateichou.theguardianreader.databinding.FragmentArticleBinding
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation

class ArticleFragment(
    private val newsEntry: NewsEntry,
    private val cardType: CardType
) : Fragment(R.layout.fragment_article) {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentArticleBinding.bind(view)

        binding.apply {
            if (cardType >= CardType.MEDIUM) {
                Glide.with(this@ArticleFragment).load(newsEntry.fields?.thumbnail)
                    .apply(RequestOptions.bitmapTransform(BrightnessFilterTransformation(-0.26f)))
                    .into(fragmentArticleImage)
            }
            fragmentArticleToolbarLayout.title = newsEntry.webTitle

            val completeBody =
                newsEntry.blocks.body.fold("") { s: String, body: Body -> s + body.bodyHtml }
            fragmentArticleContent.text =
                HtmlCompat.fromHtml(completeBody, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.article_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
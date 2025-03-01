package com.example.ieeebppimt

import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView

class RoleSelectionActivity : AppCompatActivity() {
    companion object {
        private const val SESSION_EXPIRY_TIME = 5 * 60 * 1000 // 5 minutes in milliseconds
        private const val FADE_DURATION = 300L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Force Light Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_role_selection)

        val adminCard: CardView = findViewById(R.id.adminCard)
        val userCard: CardView = findViewById(R.id.userCard)

        setSocialMediaLinks()
        animateTextSections()

        adminCard.setOnClickListener { handleAdminClick(it as CardView) }
        userCard.setOnClickListener { applyFadeAnimation(it as CardView, UserActivity::class.java) }
    }

    private fun handleAdminClick(card: CardView) {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val loginTime = sharedPreferences.getLong("loginTime", 0)
        val currentTime = System.currentTimeMillis()

        if (loginTime != 0L && currentTime - loginTime <= SESSION_EXPIRY_TIME) {
            applyFadeAnimation(card, MainActivity::class.java) // Proceed to Admin page
        } else {
            Toast.makeText(this, "Session expired. Please log in again.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun applyFadeAnimation(card: CardView, targetActivity: Class<*>) {
        card.isClickable = false
        card.animate().alpha(0.7f).setDuration(FADE_DURATION / 2).start()
        card.postDelayed({
            startActivity(Intent(this, targetActivity))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            card.isClickable = true
        }, FADE_DURATION)
    }

    private fun setSocialMediaLinks() {
        val socialLinks = mapOf(
            R.id.websiteLink to "https://ieee-bppimt.netlify.app/",
            R.id.instagramIcon to "https://www.instagram.com/ieee.bppimt/",
            R.id.linkedinIcon to "https://www.linkedin.com/company/ieee-bppimt/posts/?feedView=all",
            R.id.twitterIcon to "https://x.com/ieee_bppimt"
        )

        socialLinks.forEach { (id, url) ->
            findViewById<View>(id).setOnClickListener { openInBrowser(url) }
        }
    }

    private fun animateTextSections() {
        val textViews = listOf(
            R.id.whatIsIeee, R.id.ieeeDescription, R.id.studentBranchTitle, R.id.studentBranchDescription,
            R.id.missionTitle, R.id.missionDescription, R.id.visionTitle, R.id.visionDescription,
            R.id.activitiesTitle, R.id.activitiesDescription
        ).map { findViewById<TextView>(it) }

        textViews.forEach { it.visibility = View.INVISIBLE }
        val handler = Handler(Looper.getMainLooper())
        var delay = 200L

        textViews.forEach { textView ->
            handler.postDelayed({
                textView.visibility = View.VISIBLE
                fadeInAnimation(textView)
            }, delay)
            delay += 600L
        }
    }

    private fun fadeInAnimation(view: View) {
        ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).apply {
            duration = 800
            start()
        }
    }

    fun openLocationInMaps(view: View) {
        val mapUri = Uri.parse("geo:0,0?q=BPPIMT+College")
        val mapIntent = Intent(Intent.ACTION_VIEW, mapUri).setPackage("com.google.android.apps.maps")

        if (mapIntent.resolveActivity(packageManager) == null) {
            openInBrowser("https://www.google.com/maps/place/B.+P.+Poddar+Institute+of+Management+and+Technology/@22.575088,88.2226641,12z/data=!4m10!1m2!2m1!1sb.+p.+poddar+institute+of+management+and+technology+vip+road+mali+bagan+poodar+vihar+poddar+vihar+kolkata+west+bengal!3m6!1s0x39f89fe3b109c623:0xdfbe090bb9572f78!8m2!3d22.6294351!4d88.4345217!15sCnViLiBwLiBwb2RkYXIgaW5zdGl0dXRlIG9mIG1hbmFnZW1lbnQgYW5kIHRlY2hub2xvZ3kgdmlwIHJvYWQgbWFsaSBiYWdhbiBwb29kYXIgdmloYXIgcG9kZGFyIHZpaGFyIGtvbGthdGEgd2VzdCBiZW5nYWxadCJyYnAgcG9kZGFyIGluc3RpdHV0ZSBvZiBtYW5hZ2VtZW50IGFuZCB0ZWNobm9sb2d5IHZpcCByb2FkIG1hbGkgYmFnYW4gcG9vZGFyIHZpaGFyIHBvZGRhciB2aWhhciBrb2xrYXRhIHdlc3QgYmVuZ2FskgEHY29sbGVnZZoBJENoZERTVWhOTUc5blMwVkpRMEZuU1VOeWNWbDJWWFZuUlJBQuABAPoBBAg4EDQ!16s%2Fm%2F0j_6xw4?entry=ttu&g_ep=EgoyMDI1MDIyNC4wIKXMDSoJLDEwMjExNDUzSAFQAw%3D%3D")
        } else {
            startActivity(mapIntent)
        }
    }

    private fun openInBrowser(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}

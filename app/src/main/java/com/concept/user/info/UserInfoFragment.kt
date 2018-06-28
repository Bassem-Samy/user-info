package com.concept.user.info


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.concept.user.R
import com.concept.user.application.UserApplication
import com.concept.user.info.di.UserInfoModule
import com.concept.user.info.ui.models.presenter.UserInfoPresenter
import com.concept.user.info.ui.models.view.UserInfoView
import com.concept.user.util.ImageLoader
import kotlinx.android.synthetic.main.fragment_user_info.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [UserInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class UserInfoFragment : Fragment(), UserInfoView {

    @Inject
    lateinit var imageLoader: ImageLoader
    @Inject
    lateinit var presenter: UserInfoPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeDependencies()
        presenter.getUserInfo()
    }

    private fun initializeDependencies() {
        activity?.let {
            UserApplication.get(it.application)
                    .getApplicationComponent()
                    ?.plus(UserInfoModule(this))
                    ?.inject(this)
        }
    }

    override fun showLoadingUserInfo() {
    }

    override fun showFailedToLoadUserInfo() {
    }

    override fun hideLoadingUserInfo() {

    }


    @SuppressLint("StringFormatInvalid")
    override fun displayUserName(firstName: String?, lastName: String?) {
        user_name_text_view.text = getString(R.string.user_profile_name_formatted_text,
                firstName,
                lastName)
    }

    override fun displayEmail(email: String?) {
        if (!TextUtils.isEmpty(email)) {
            user_email_text_view.text = email
            Linkify.addLinks(user_email_text_view, Linkify.EMAIL_ADDRESSES)
        } else {
            user_email_text_view.setText(R.string.empty_user_info_field_default_text)
        }
    }

    override fun displayProfileImage(url: String?) {
        imageLoader.loadImageUrl(user_profile_image_view, url, R.drawable.user_placeholder)
    }

    override fun displayPhoneNumber(phoneNumber: String?) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            user_phone_number_text_view.text = phoneNumber
            Linkify.addLinks(user_phone_number_text_view, Linkify.PHONE_NUMBERS)
        } else {
            user_phone_number_text_view.setText(R.string.empty_user_info_field_default_text)
        }
    }

    override fun showUserInfoLayout() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment UserInfoFragment.
         */
        const val TAG = "user_info_fragment"

        @JvmStatic
        fun newInstance() = UserInfoFragment()

    }
    /* animations
    android:animateLayoutChanges="true"
https://android--examples.blogspot.com/2017/02/android-fade-slide-explode-transition.html

    TransitionManager.beginDelayedTransition(mCLayout,makeExplodeTransition());
                toggleVisibility(mImageView,mImageViewSecond);
                to do: ui then tests
                implement delete!
     */

}

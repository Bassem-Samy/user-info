package com.concept.user.info


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.concept.user.R
import com.concept.user.application.UserApplication
import com.concept.user.info.di.UserInfoModule
import com.concept.user.info.ui.models.presenter.UserInfoPresenter
import com.concept.user.info.ui.models.view.UserInfoView
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [UserInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class UserInfoFragment : Fragment(), UserInfoView {
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

    override fun displayFirstName(firstName: String?) {

    }

    override fun displayLastName(lastName: String?) {

    }

    override fun displayEmail(email: String?) {

    }

    override fun displayProfileImage(url: String?) {

    }

    override fun displayPhoneNumber(phoneNumber: String?) {

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
}

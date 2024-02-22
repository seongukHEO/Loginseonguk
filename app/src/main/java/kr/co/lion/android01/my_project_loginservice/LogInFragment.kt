package kr.co.lion.android01.my_project_loginservice

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentLogInBinding
import kr.co.lion.android01.newmemoproject_seonguk.enum
import java.util.regex.Pattern

class LogInFragment : Fragment() {

    lateinit var fragmentLogInBinding: FragmentLogInBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentLogInBinding = FragmentLogInBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()
        setEvent()

        return fragmentLogInBinding.root
    }

    //툴바 설정
    fun setToolBar(){
        fragmentLogInBinding.apply {
            materialToolbar2.apply {
                title = "회원 가입"
                //아이콘
                setNavigationIcon(R.drawable.arrow_back_24px)
                //클릭
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.LOGIN_FRAGMENT)
                }
            }
        }
    }

    //이벤트 설정
    fun setEvent(){
        fragmentLogInBinding.apply {
            newMemberLoginButton.setOnClickListener {
                checkOK()
            }
        }

    }
    //입력을 받는다
    fun inputData(){
        fragmentLogInBinding.apply {
            var userName = nameLoginText.text.toString()
            var userNumber = numberLoginText.text.toString().toInt()
            var userId = idLoginText.text.toString()
            var userPw = pwLoginText.text.toString()
            var checkPW = checkPWLoginText.text.toString()

            var loginList = LoginClass(1, userId, userPw, userName, userNumber)
            LoginDAO.insertLogin(mainActivity, loginList)


        }

    }
    //유효성 검사
    fun checkOK(){
        fragmentLogInBinding.apply {
            var name = nameLoginText.text.toString()
            if (name.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "이름 입력 오류", "이름을 입력 해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(nameLoginText, mainActivity)
                }
                return
            }

            var number = numberLoginText.text.toString()
            if (number.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "휴대폰 번호 입력 오류", "휴대폰 번호를 입력 해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(numberLoginText, mainActivity)
                }
                return
            }

            var id = idLoginText.text.toString()
            var useridInfo = LoginDAO.selectLoginOne(mainActivity, id)
            if (id.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "아이디 입력 오류", "아이디를 입력 해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(idLoginText, mainActivity)
                }
                return
            }else if(id == useridInfo?.userId){
                enum.showDiaLog(mainActivity, "아이디 중복 오류", "이미 존재하는 아이디 입니다. 다른 아이디를 입력해주세요!"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(idLoginText, mainActivity)
                }
                return
            }


            var pw = pwLoginText.text.toString()
            var special123 = Pattern.compile("[!@#$%^&*()<>{}|;:]")
            var matchers = special123.matcher(pw)
            if (pw.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "비밀번호 입력 오류", "비밀번호를 입력 해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(pwLoginText, mainActivity)
                }
                return
            }else if (!matchers.find()){
                enum.showDiaLog(mainActivity, "특수문자 입력 오류", "특수문자를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(pwLoginText, mainActivity)
                }
                return
            }


            var checkPW = checkPWLoginText.text.toString()
            if (pw != checkPW){
                enum.showDiaLog(mainActivity, "비밀번호 오류", "비밀번호와 일치하지 않습니다"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(checkPWLoginText, mainActivity)
                }
                return
            }
            enum.hideSoftInput(mainActivity)
        }
        inputData()
        mainActivity.removeFragment(FragmentName.LOGIN_FRAGMENT)

    }
}




































package kr.co.lion.android01.my_project_loginservice

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentModifyBinding
import kr.co.lion.android01.newmemoproject_seonguk.enum

class ModifyFragment : Fragment() {

    lateinit var fragmentModifyBinding: FragmentModifyBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentModifyBinding = FragmentModifyBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()
        setView()
        setEvent()
        return fragmentModifyBinding.root
    }
    //툴바 설정
    fun setToolBar(){
        fragmentModifyBinding.apply {
            materialToolbar5.apply {
                title = "나의 정보 수정"
                setNavigationIcon(R.drawable.finmyloggo_gh)
            }
        }
    }

    //이벤트 설정
    fun setEvent(){
        fragmentModifyBinding.apply {
            modifyButton.setOnClickListener {
                enum.showDiaLog(mainActivity, "정보 수정", "정말 수정하시겠습니까?"){ dialogInterface: DialogInterface, i: Int ->
                    checkOK()
                }
            }
            deleteButton.setOnClickListener {
                enum.showDiaLog(mainActivity, "정보 삭제", "정말 삭제하시겠습니까?"){ dialogInterface: DialogInterface, i: Int ->
                    var info = arguments?.getString("userId")
                    if (info != null){
                        InfoDAO.delete(mainActivity, info)
                        mainActivity.removeFragment(FragmentName.MODIFY_FRAGMENT)
                    }
                }
            }
        }

    }

    //뷰 설정
    fun setView(){
        fragmentModifyBinding.apply {
            var info = arguments?.getString("userId")
            if (info != null){
                var userInfo = InfoDAO.getUserWithAdditionalInfo(mainActivity, info)

                textModifyId.setText("${userInfo?.userId}")
                heightmodifyText.setText("${userInfo?.height}")
                weightmodifyText.setText("${userInfo?.weight}")
                agemodifyText.setText("${userInfo?.age}")
                bmimodifyText.setText("${userInfo?.bmi}")
                bonemodifyText.setText("${userInfo?.bone}")
            }
        }
    }
    //입력받는다
    fun inputData(){
        fragmentModifyBinding.apply {
            var info = arguments?.getString("userId")
            var userInfo = InfoDAO.getUserWithAdditionalInfo(mainActivity, info!!)

            var userId = textModifyId.text.toString()
            var height = heightmodifyText.text.toString().toInt()
            var wight = weightmodifyText.text.toString().toInt()
            var age = agemodifyText.text.toString().toInt()
            var bmi = bmimodifyText.text.toString().toInt()
            var bone = bonemodifyText.text.toString().toInt()

            InfoDAO.delete(mainActivity, userInfo?.userId!!)
            var infoList = UserInfo(1, userId, height, age, wight, bmi, bone)
            InfoDAO.insertInfo(mainActivity, infoList)
        }
    }

    //유효성 검사
    fun checkOK(){
        fragmentModifyBinding.apply {
            var height = heightmodifyText.text.toString()
            if (height.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "키 입력 오류", "키를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(heightmodifyText, mainActivity)
                }
                return
            }

            var weight = weightmodifyText.text.toString()
            if (weight.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "몸무게 입력 오류", "몸무게를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(weightmodifyText, mainActivity)
                }
                return
            }

            var age = agemodifyText.text.toString()
            if (age.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "나이 입력 오류", "나이를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(agemodifyText, mainActivity)
                }
                return
            }

            var bmi = bmimodifyText.text.toString()
            if (bmi.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "BMI 입력 오류", "BMI를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(bmimodifyText, mainActivity)
                }
                return
            }

            var bone = bonemodifyText.text.toString()
            if (bone.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "골격근량 입력 오류", "골격근량을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(bonemodifyText, mainActivity)
                }
                return
            }
        }
        inputData()
        mainActivity.removeFragment(FragmentName.MODIFY_FRAGMENT)
    }
}





































package kr.co.lion.android01.my_project_loginservice

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentBottomShowBinding
import kr.co.lion.android01.newmemoproject_seonguk.enum

class BottomShowFragment : BottomSheetDialogFragment() {

    lateinit var fragmentBottomShowBinding: FragmentBottomShowBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentBottomShowBinding = FragmentBottomShowBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        showData()
        setEvent()
        return fragmentBottomShowBinding.root
    }

    //내용을 보여준다
    fun showData(){
        fragmentBottomShowBinding.apply {
            bottomDateText.text = "2월 21일"
            bottomTImeText.text = "30분"
            bottomBodyText.text = "가슴"
            bottomExtraText.text = "후 힘들어따"

        }
    }
    //이벤트 설정
    fun setEvent(){
        fragmentBottomShowBinding.apply {
            bottomModifyButton.setOnClickListener {
                mainActivity.replaceFragment(FragmentName.MEMO_MODIFY_FRAGMENT, true, true, null)
                dismiss()

            }
            bottomDeleteButton.setOnClickListener {
                enum.showDiaLog(mainActivity, "메모 삭제", "정말 삭제하시겠습니까?"){ dialogInterface: DialogInterface, i: Int ->
                    dismiss()
                }
            }
            bottomCancelButton.setOnClickListener {
                dismiss()
            }
        }
    }

}



















































package com.airekeola.waterresourceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.setPadding
import com.airekeola.waterresourceapp.databinding.ActivityMainBinding
import com.airekeola.waterresourceapp.model.WaterResource

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding;
    private lateinit var waterResources: MutableList<WaterResource>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        this.viewBinding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(this.viewBinding.root);

        this.initialize();
    }

    private fun initialize() {
        this.waterResources = mutableListOf(
            WaterResource("River Ganges", "Varanasi, India", "River"),
            WaterResource("Lake Michigan", "Chicago, Illinois, USA", "Lake"),
        );
        this.waterResources.forEach { r -> this.addToTable(r) };

        // bind event handlers
        viewBinding.addBtn.setOnClickListener(this::onAddBtnClick);
    }

    private fun onAddBtnClick(view: View){
        val name = viewBinding.nameText.text.toString();
        val location = viewBinding.locationText.text.toString();
        val type = viewBinding.typeText.text.toString();

        if(name.isEmpty() || location.isEmpty() || type.isEmpty()){
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
        }else{
            addToTable(WaterResource(name, location, type));
            viewBinding.nameText.text.clear();
            viewBinding.locationText.text.clear();
            viewBinding.typeText.text.clear();
            Toast.makeText(this, "Water resource added successfully.", Toast.LENGTH_SHORT).show();
        }
    }

    private fun addToTable(resource: WaterResource) {
        val textViews = mutableListOf<TextView>(
            TextView(this).apply {
                text = resource.name;
                this.setPadding(dpToPx(4),dpToPx(6),dpToPx(4),dpToPx(6));
                this.setBackgroundResource(R.drawable.border);
            },
            TextView(this).apply {
                text = resource.location;
                this.setPadding(dpToPx(4),dpToPx(6),dpToPx(4),dpToPx(6));
                this.setBackgroundResource(R.drawable.border);
            },
            TextView(this).apply {
                text = resource.type;
                this.setPadding(dpToPx(4),dpToPx(6),dpToPx(4),dpToPx(6));
                this.setBackgroundResource(R.drawable.border);
            }
        );

        val row = TableRow(this);
        textViews.forEach { tv -> row.addView((tv)) }
        viewBinding.table.addView(row);
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}

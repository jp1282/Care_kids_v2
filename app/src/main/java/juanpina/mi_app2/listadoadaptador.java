package juanpina.mi_app2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import juanpina.care_kids.R;

public class listadoadaptador extends RecyclerView.Adapter<listadoadaptador.ViewHolder> {
    private List<listadoelementos> mData;
    private LayoutInflater mInflater;
    private Context context;


    public listadoadaptador (List<listadoelementos> itemList, Context context){

        this.mInflater=LayoutInflater.from(context);
        this.context=context;
        this.mData = itemList;
    }

   // @Override
    public int getItemcount() {return mData.size();}

    @Override
    public listadoadaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.listado, null);
        return new listadoadaptador.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final listadoadaptador.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
        /*holder.btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "mensaje de prueba");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public void setItems(List<listadoelementos> items ){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView IconImage;
        TextView nombre, numero;
        Button btnEnviar;

        ViewHolder (View itemView){
            super (itemView);
            IconImage = itemView.findViewById(R.id.imagencontacto);
            nombre = itemView.findViewById(R.id.tv2);
            numero = itemView.findViewById(R.id.tv3);
            btnEnviar = itemView.findViewById(R.id.btnEnviar);
        }

        void bindData(final listadoelementos item){
            nombre.setText(item.getNombre());
            numero.setText(item.getNumero());
        }

    }
}

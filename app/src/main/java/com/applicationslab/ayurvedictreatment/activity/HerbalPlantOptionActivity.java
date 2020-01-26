package com.applicationslab.ayurvedictreatment.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.appdata.StaticData;
import com.applicationslab.ayurvedictreatment.datamodel.HerbalPlantsData;

import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by user on 6/6/2016.
 */
public class HerbalPlantOptionActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnHerbalPlants;
    Button btnDiseases;

    ArrayList<HerbalPlantsData> herbalPlantsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herbal_plant_option);
        initView();
        prepareData();
        initData();
        setUIClickHandler();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        Toolbar toolBar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Herbal Plants");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnHerbalPlants = (Button) findViewById(R.id.btnHerbalPlants);
        btnDiseases = (Button) findViewById(R.id.btnDiseases);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        btnDiseases.setTypeface(tfRegular, Typeface.BOLD);
        btnHerbalPlants.setTypeface(tfRegular, Typeface.BOLD);
    }

    private void initData() {
        StaticData.setHerbalPlantsDatas(herbalPlantsData);
    }

    private void setUIClickHandler() {
        btnHerbalPlants.setOnClickListener(this);
        btnDiseases.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnHerbalPlants) {
            startActivity(new Intent(HerbalPlantOptionActivity.this, SearchHerbalPlantActivity.class));
        } else if(v.getId() == R.id.btnDiseases) {
            startActivity(new Intent(HerbalPlantOptionActivity.this, SearchDiseasesActivity.class));
        }
    }


    private void prepareData() {
        herbalPlantsData = new ArrayList<>();
        HerbalPlantsData herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Neem");
        herbalPlantsRow.setScientificName("Azadirachta indica");
        herbalPlantsRow.setDetails("Neem is a fast-growing tree that can reach a height of 15–20 metres (49–66 ft), rarely to 35–40 metres (115–130 ft). It is evergreen, but in severe drought it may shed most or nearly all of its leaves. The branches are wide and spreading. it is believed to be good for one's health. Neem gum is a rich source of protein. In Myanmar, young neem leaves and flower buds are boiled with tamarind fruit to soften its bitterness and eaten as a vegetable. Pickled neem leaves are also eaten with tomato and fish paste sauce in Myanmar.");
        herbalPlantsRow.setUses(">> It is considered a major component in Ayurvedic  and Unani medicine and is particularly prescribed for skin diseases. \n\n" +
                ">> Neem oil is also used for healthy hair, to improve liver function, detoxify the blood, and balance blood sugar levels.\n\n" +
                ">> Neem leaves have been also been used to treat skin diseases like eczema, psoriasis, etc.");
        herbalPlantsRow.setImageId(R.drawable.neem);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Aloe Vera");
        herbalPlantsRow.setScientificName("");
        herbalPlantsRow.setDetails("Aloe vera is a stemless or very short-stemmed succulent plant growing to 60–100 cm (24–39 in) tall, spreading by offsets. The leaves are thick and fleshy, green to grey-green, with some varieties showing white flecks on their upper and lower stem surfaces.[6] The margin of the leaf is serrated and has small white teeth.");
        herbalPlantsRow.setUses(">> There is some preliminary evidence to suggest that oral administration of aloe vera might be effective in reducing blood glucose in diabetic patients and in lowering blood lipid levels in hyperlipidaemia.\n\n" +
                ">> Aloe vera, called kathalai in Ayurvedic medicine, is used as a multipurpose skin treatment.");
        herbalPlantsRow.setImageId(R.drawable.aloe_vera);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Bitter Gourd");
        herbalPlantsRow.setScientificName("Momordica charantia");
        herbalPlantsRow.setDetails("Bitter melon comes in a variety of shapes and sizes. The cultivar common to China is 20–30 cm long, oblong with bluntly tapering ends and pale green in color, with a gently undulating, warty surface. The bitter melon more typical of India has a narrower shape with pointed ends, and a surface covered with jagged, triangular \"teeth\" and ridges. It is green to white in color. Between these two extremes are any number of intermediate forms. Some bear miniature fruit of only 6–10 cm in length, which may be served individually as stuffed vegetables.");
        herbalPlantsRow.setUses(">> It has been used as a folk remedy for a variety of ailments, particularly stomach complaints.\n\n" +
                ">> Is used as an agent to reduce the blood glucose level.");
        herbalPlantsRow.setImageId(R.drawable.bitter_gourd);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Garlic");
        herbalPlantsRow.setScientificName("Allium sativum");
        herbalPlantsRow.setDetails("Allium sativum, commonly known as garlic, is a species in the onion genus. Garlic is easy to grow and can be grown year-round in mild climates.");
        herbalPlantsRow.setUses(">> Garlic is also alleged to help regulate blood sugar levels. Regular and prolonged use of therapeutic amounts of aged garlic extracts lower blood homocysteine levels and has been shown to prevent some complications of diabetes mellitus.\n\n" +
                ">> Garlic was used as an antiseptic to prevent gangrene.\n\n" +
                ">> Garlic cloves are used as a remedy for infections (especially chest problems), digestive disorders, and fungal infections.");
        herbalPlantsRow.setImageId(R.drawable.garlic);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Black Cohosh");
        herbalPlantsRow.setScientificName("");
        herbalPlantsRow.setDetails("Black cohosh is a smooth (glabrous) herbaceous perennial plant that produces large, compound leaves from an underground rhizome, reaching a height of 25–60 centimetres (9.8–23.6 in).[2][3] The basal leaves are up to 1 metre (3 ft 3 in) long and broad, forming repeated sets of three leaflets (tripinnately compound) having a coarsely toothed (serrated) margin. The flowers are produced in late spring and early summer on a tall stem, 75–250 centimetres (30–98 in) tall, forming racemes up to 50 centimetres (20 in) long. The flowers have no petals or sepals, and consist of tight clusters of 55-110 white, 5–10 mm long stamens surrounding a white stigma. The flowers have a distinctly sweet, fetid smell that attracts flies, gnats, and beetles.[2] The fruit is a dry follicle 5–10 mm long, with one carpel, containing several seeds.");
        herbalPlantsRow.setUses("It is used to treat gynecological and other disorders, including sore throats, kidney problems, and depression.");
        herbalPlantsRow.setImageId(R.drawable.black_cohos);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Bitter Leaf");
        herbalPlantsRow.setScientificName("Vernonia amygdalina");
        herbalPlantsRow.setDetails("Vernonia is a genus of about 1000 species of forbs and shrubs in the family Asteraceae. Some species are known as Ironweed. Some species are edible and of economic value. They are known for having intense purple flowers. The genus is named for English botanist William Vernon. There are numerous distinct subgenera and subsections in this genus.");
        herbalPlantsRow.setUses("amygdalina is well known as a medicinal plant with several uses attributed to it, including for diabetes, fever reduction, and recently a non-pharmaceutical solution to persistent fever, headache, and joint pain associated with AIDS (an infusion of the plant is taken as needed).");
        herbalPlantsRow.setImageId(R.drawable.bitter_leaf);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Amla");
        herbalPlantsRow.setScientificName("");
        herbalPlantsRow.setDetails("Amla is a place in Madhya Pradesh in India.Amla junction is 38 km from Betul on rail route towards Nagpur . By road Amla is about 35 km from Betul.Amla is bordered to the south by low lying ridges of Deccan Trap. Northwestern part is also a hilly terrain with height up to 843 m from M.S.L. The area in between is mostly plain with undulatory topography formed by narrow strike ridges. General ground level is 720-740 m above M.S.L.The region enjoys hot & humid dry subtropical climate with maximum average temperature of 32°C. Southeast monsoon remains active during June to September. The average rainfall in the area is 1005 mm. The summer lasts between March and June whereas winter prevails between October and February. Occasionally temperature touches >40°C during summer.");
        herbalPlantsRow.setUses("Although Ayurvedic doctors have a great deal of experience with the use of Amla, little human research is available in the West. Research has been done with amla evaluating its role as an antioxidant, in ulcer prevention, for people with diabetes, for mental and memory effects, and its anti-inflammatory benefits.");
        herbalPlantsRow.setImageId(R.drawable.amla);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Arjun");
        herbalPlantsRow.setScientificName("");
        herbalPlantsRow.setDetails("The arjuna is about 20–25 metres tall; usually has a buttressed trunk, and forms a wide canopy at the crown, from which branches drop downwards. It has oblong, conical leaves which are green on the top and brown below; smooth, grey bark; it has pale yellow flowers which appear between March and June; its glabrous, 2.5 to 5 cm fibrous woody fruit, divided into five wings, appears between September and November.");
        herbalPlantsRow.setUses("Heart disorders, low semen density, coughs,blood purifier, leucorrhea. Bark is soaked in water overnight. The following morning, thewater is taken orally with sugarcane molasses in the form of a sherbet.");
        herbalPlantsRow.setImageId(R.drawable.arjun);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Ashok");
        herbalPlantsRow.setScientificName("");
        herbalPlantsRow.setDetails("The ashoka is a rain-forest tree. Its original distribution was in the central areas of the Deccan plateau, as well as the middle section of the Western Ghats in the western coastal zone of the Indian subcontinent.The ashoka is prized for its beautiful foliage and fragrant flowers. It is a handsome, small, erect evergreen tree, with deep green leaves growing in dense clusters.");
        herbalPlantsRow.setUses("Rise of blood pressure during night. Bark of Abroma augusta is mixed with bark of Saraca asoca, bark or root of Rauwolfia serpentina andthen powdered or boiled in water. The powder or water is taken for 7 days.");
        herbalPlantsRow.setImageId(R.drawable.herbal_plant);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Bashok");
        herbalPlantsRow.setScientificName("");
        herbalPlantsRow.setDetails("Bashoka consists of the fresh or dried leaves of Adhatoda Vasica, Nees (N.O. Acanthaceae), a shrub growing in India. The leaves vary from 10 to 15 centimeters in length, and are about 4 centimeters broad; they are opposite, entire, lanceolate, and shortly petiolate, tapering towards both apex and base. When dry they are of a dull brownish-green colour; odour, characteristic; taste, bitter.");
        herbalPlantsRow.setUses("It is used in Coughs, mucus, fever. Juice obtained fromcrushed leaves is taken orally.Tuberculosis, passing of blood through themouth due to lung disorders. Dried andpowdered bark is taken orally.");
        herbalPlantsRow.setImageId(R.drawable.bashok);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Thankuni Pata");
        herbalPlantsRow.setScientificName("");
        herbalPlantsRow.setDetails("Thankuni  grows in tropical swampy areas.[3] The stems are slender, creeping stolons, green to reddish-green in color, connecting plants to each other. It has long-stalked, green, reniform leaves with rounded apices which have smooth texture with palmately netted veins. The leaves are borne on pericladial petioles, around 2 cm.");
        herbalPlantsRow.setUses("It is used in Bloating, gastrointestinal disorders (diarrhea,dysentery), to increase memory. Juice obtain from crushed leaves and stems is taken till cure.");
        herbalPlantsRow.setImageId(R.drawable.thankuni_pata);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Swarna Lota");
        herbalPlantsRow.setScientificName("");
        herbalPlantsRow.setDetails("This ornamental flowering plant is having several common names, such as peacock flower, Pride of Barbados, Dwarf poinciana, etc. In our country Bangladesh.");
        herbalPlantsRow.setUses("It is used in Gastric troubles. Juice obtained from crushedstems is orally taken with sugarcane molassesfor 7 days.");
        herbalPlantsRow.setImageId(R.drawable.swarna_lata);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Tith Baegun");
        herbalPlantsRow.setScientificName("");
        herbalPlantsRow.setDetails("The plant is usually 2 or 3 m in height and 2 cm in basal diameter, but may reach 5m in height and 8 cm in basal diameter. The shrub usually has a single stem at ground level, but it may branch on the lower stem. The stem bark is gray and nearly smooth with raised lenticels. The inner bark has a green layer over an ivory color (Little and others 1974). The plants examined by the author, growing on firm soil, had weak taproots and well-developed laterals. The roots are white.");
        herbalPlantsRow.setUses("It is used in Fever, coughs, loss of appetite, leprosy, heartdisorders. Juice obtained from whole plant istaken orally.Entering of thorn in any part of the body. Rootsare inserted into a banana fruit and taken orally in the morning (thorns come out by themselvesfollowing this procedure).");
        herbalPlantsRow.setImageId(R.drawable.tith_begun);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Beal");
        herbalPlantsRow.setScientificName("");
        herbalPlantsRow.setDetails("Gastroduodenal ulcers are the most common form of gastric ulcers. Such ulcers develop when there is an imbalance in the acid mucosa levels, or due to oxidative stress along the gastric tract. Experiments gave positive results in gastric ulcer inhibition. The phenolic compounds found in bael extracts possessed potent antioxidants which helped in reducing gastric ulcers.");
        herbalPlantsRow.setUses(">> Oxidative stress induced hyerglacemia or diabetes can be reduced to a great extent by extracts of bael leaf. Studies have shown that administering leaf extracts of bael reduced blood glucose levels up to 54%.\n\n" +
                ">> Bael leaf extracts were studied for their cholesterol control. They were effective in decreasing blood cholesterol levels comparable to modern drugs. Bael leaf extracts controlled not just blood cholesterol levels but also triglycerides and serum and tissue lipid profiles.");
        herbalPlantsRow.setImageId(R.drawable.beal);
        herbalPlantsData.add(herbalPlantsRow);
        herbalPlantsRow = new HerbalPlantsData();
        herbalPlantsRow.setName("Tulsi");
        herbalPlantsRow.setScientificName("");
        herbalPlantsRow.setDetails("The tulsi plant has many medicinal properties. The leaves are a nerve tonic and also sharpen memory. They promote the removal of the catarrhal matter and phlegm from the bronchial tube. The leaves strengthen the stomach and induce copious perspiration. The seed of the plant are mucilaginous.");
        herbalPlantsRow.setUses(">> Tulsi is an important constituent of many Ayurvedic cough syrups and expectorants. It helps to mobilize mucus in bronchitis and asthma. Chewing tulsi leaves relieves cold and flu.\n\n " +
                ">> Water boiled with basil leaves can be taken as drink in case of sore throat. This water can also be used as a gargle.\n\n" +
                ">> Basil has strengthening effect on the kidney. In case of renal stone the juice of basil leaves and honey, if taken regularly for 6 months it will expel them via the urinary tract.");
        herbalPlantsRow.setImageId(R.drawable.tulsi);
        herbalPlantsData.add(herbalPlantsRow);
    }


}

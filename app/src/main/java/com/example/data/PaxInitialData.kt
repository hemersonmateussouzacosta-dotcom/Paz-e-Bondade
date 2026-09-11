package com.example.data

import com.example.data.model.AudioPackage
import com.example.data.model.FranciscanSaint
import com.example.data.model.LiturgicalDay1962
import com.example.data.model.ReminderSetting
import com.example.data.model.SeraphicPrayer

object PaxInitialData {

    fun getInitialSaints(): List<FranciscanSaint> = listOf(
        FranciscanSaint(
            id = 1,
            name = "São Francisco de Assis",
            feastMonth = 10,
            feastDay = 4,
            orderType = "OFM (1ª Ordem)",
            years = "1182 – 1226",
            title = "Pai Seráfico, Fundador das Três Ordens Franciscanas",
            biography = "Nascido em Assis, na Úmbria, Francisco abandonou as riquezas do pai terreno para seguir a Cristo na mais perfeita pobreza evangélica. Recebeu do crucifixo de São Damião o apelo: 'Francisco, vai e restaura a minha Igreja'. Fundou os Frades Menores, as Clarissas com Santa Clara e a Ordem Franciscana Secular. Em 1224, no Monte Alverne, recebeu no próprio corpo os Sagrados Estigmas da Paixão de Cristo.",
            quote = "Comece fazendo o que é necessário, depois o que é possível, e de repente você estará fazendo o impossível. Deus meus et omnia!",
            patronage = "Padroeiro da ecologia, da Itália, dos animais e das Ordens Franciscanas."
        ),
        FranciscanSaint(
            id = 2,
            name = "Santa Clara de Assis",
            feastMonth = 8,
            feastDay = 11,
            orderType = "Clarissa (2ª Ordem)",
            years = "1194 – 1253",
            title = "Virgem Seráfica e Fundadora da Ordem das Clarissas",
            biography = "Jovem nobre de Assis, Clara foi profundamente tocada pela pregação de São Francisco. Na noite do Domingo de Ramos de 1212, fugiu de casa para consagrar-se inteiramente a Deus na igrejinha da Porciúncula. Estabeleceu no mosteiro de São Damião o 'Privilégio da Pobreza'. Com o Santíssimo Sacramento nas mãos, afugentou os invasores sarracenos que sitiavam o convento.",
            quote = "Não perca de vista o vosso ponto de partida; conserveis o que tendes; façais o que fazeis, e não o deixeis. Olha para o Espelho todos os dias: Jesus Cristo.",
            patronage = "Padroeira da televisão, dos meios de comunicação e dos clarividentes da fé."
        ),
        FranciscanSaint(
            id = 3,
            name = "Santo Antônio de Pádua e Lisboa",
            feastMonth = 6,
            feastDay = 13,
            orderType = "OFM (1ª Ordem)",
            years = "1195 – 1231",
            title = "Doutor Evangélico e Pregador Seráfico",
            biography = "Nascido em Lisboa sob o nome Fernando, ingressou primeiro nos Cônegos Regulares e, ao contemplar o martírio dos primeiros franciscanos em Marrocos, fez-se Frade Menor com o nome de Antônio. Ilustre pregador em Roma, França e Itália, combateu heresias com extraordinária ciência bíblica e incontáveis milagres.",
            quote = "A caridade é a alma da fé, torna-a viva; sem o amor, a fé morre. Quem tem a verdade no coração fala a língua do Espírito Santo.",
            patronage = "Padroeiro dos pobres, das causas perdidas, dos casamentos e de Portugal."
        ),
        FranciscanSaint(
            id = 4,
            name = "São Padre Pio de Pietrelcina",
            feastMonth = 9,
            feastDay = 23,
            orderType = "OFM Cap (Capuchinho)",
            years = "1887 – 1968",
            title = "Sacerdote Estigmatizado e Místico do Século XX",
            biography = "Francesco Forgione entrou no noviciado capuchinho aos 15 anos. Em San Giovanni Rotondo, viveu uma vida de intensa oração, vigílias e confessionário. Em 1918 recebeu os estigmas visíveis de Jesus, que carregou durante cinquenta anos até sua santa morte. Fundou a Casa Alívio do Sofrimento e grupos de oração no mundo inteiro.",
            quote = "Reze, tenha fé e não se preocupe. A preocupação é inútil. Deus é misericordioso e ouvirá a sua oração.",
            patronage = "Padroeiro dos confessores, doentes e voluntários da caridade."
        ),
        FranciscanSaint(
            id = 5,
            name = "São Maximiliano Maria Kolbe",
            feastMonth = 8,
            feastDay = 14,
            orderType = "OFM Conv (Conventual)",
            years = "1894 – 1941",
            title = "Mártir da Caridade e Apóstolo da Imaculada",
            biography = "Frade menor conventual polonês, fundou a Milícia da Imaculada e a grandiosa Cidade da Imaculada (Niepokalanów). Enviado ao campo de concentração nazista de Auschwitz, ofereceu voluntariamente sua própria vida para salvar a de um pai de família condenado ao bunker de fome.",
            quote = "Não esqueçais que a santidade não consiste em ações extraordinárias, mas em cumprir com amor a vontade de Deus.",
            patronage = "Padroeiro dos jornalistas, dos prisioneiros e das famílias."
        ),
        FranciscanSaint(
            id = 6,
            name = "Santa Isabel da Hungria",
            feastMonth = 11,
            feastDay = 19,
            orderType = "OFS (3ª Ordem Secular)",
            years = "1207 – 1231",
            title = "Princesa Real e Padroeira da Ordem Franciscana Secular",
            biography = "Filha do rei da Hungria e esposa do Landgrave da Turíngia, Isabel usava sua posição real para alimentar e vestir os pobres pessoalmente. Quando viúva, vestiu o hábito cinzento da Ordem Terceira Franciscana e construiu um hospital com seus próprios bens, servindo aos leprosos e enfermos com as próprias mãos.",
            quote = "Devemos fazer as pessoas felizes! O que dermos aos necessitados, estamos dando diretamente às mãos feridas de Cristo.",
            patronage = "Padroeira da Ordem Franciscana Secular, da caridade e dos hospitais."
        ),
        FranciscanSaint(
            id = 7,
            name = "São Boaventura de Bagnoregio",
            feastMonth = 7,
            feastDay = 14,
            orderType = "OFM (1ª Ordem)",
            years = "1221 – 1274",
            title = "Doutor Seráfico e Segundo Fundador da Ordem",
            biography = "Filósofo e teólogo na Universidade de Paris, tornou-se Ministro Geral da Ordem dos Frades Menores em momento de crise, pacificando os espíritos com sabedoria incomparável. Autor da 'Legenda Maior' de São Francisco e do 'Itinerário da Mente para Deus'. Foi criado Cardeal Bispo de Albano.",
            quote = "A mente pode alcançar a sabedoria quando a oração se une ao estudo e a piedade à ciência.",
            patronage = "Padroeiro dos teólogos franciscanos e estudantes universitários."
        ),
        FranciscanSaint(
            id = 8,
            name = "São Leopoldo Mandic",
            feastMonth = 5,
            feastDay = 12,
            orderType = "OFM Cap (Capuchinho)",
            years = "1866 – 1942",
            title = "Apóstolo da Misericórdia e Confessor de Pádua",
            biography = "Capuchinho de frágil saúde física mas de alma heróica, passou mais de 30 anos no pequeno confessionário em Pádua, acolhendo milhares de almas durante até doze horas diárias, administrando o perdão de Cristo com ternura inalterável.",
            quote = "Tenha confiança, tudo correrá bem. Deus é Pai e não abandona seus filhos arrependidos.",
            patronage = "Padroeiro da reconciliação, dos penitentes e dos doentes de câncer."
        ),
        FranciscanSaint(
            id = 9,
            name = "São Pedro de Alcântara",
            feastMonth = 10,
            feastDay = 19,
            orderType = "OFM (1ª Ordem)",
            years = "1499 – 1562",
            title = "Místico da Estrita Observância e Padroeiro do Brasil",
            biography = "Frade penitente espanhol de severidade evangélica, dormia apenas uma hora e meia por noite sentado com a fronte encostada a um tronco. Foi diretor espiritual de Santa Teresa de Jesus na reforma carmelita. Por decreto papal, foi declarado Padroeiro Principal do Brasil em 1826.",
            quote = "A paz interior é o maior tesouro da alma. Ninguém a pode tirar senão nós mesmos pelo pecado.",
            patronage = "Padroeiro Principal do Brasil e dos guardas noturnos."
        ),
        FranciscanSaint(
            id = 10,
            name = "São Luís IX, Rei de França",
            feastMonth = 8,
            feastDay = 25,
            orderType = "OFS (3ª Ordem Secular)",
            years = "1214 – 1270",
            title = "Monarca Santo e Padroeiro da Terceira Ordem Franciscana",
            biography = "Rei exemplar, governou a França com espírito franciscano de justiça, mansidão e socorro aos pobres, a quem convidava para jantar em sua mesa real. Ingressou na Ordem Terceira da Penitência de São Francisco e preservou com amor as relíquias da Coroa de Espinhos de Nosso Senhor.",
            quote = "Filho meu, tem o coração tão compassivo para com os pobres que estejas pronto a consolá-los e socorrê-los sempre.",
            patronage = "Padroeiro dos governantes justos e da Ordem Franciscana Secular."
        )
    )

    fun getInitialPrayers(): List<SeraphicPrayer> = listOf(
        SeraphicPrayer(
            id = 1,
            title = "Oração da Paz (Oração de São Francisco)",
            latinTitle = "Oratio pro Pace Franciscana",
            category = "Paz",
            portugueseText = "Senhor, fazei de mim um instrumento de vossa paz.\n\nOnde houver ódio, que eu leve o amor;\nOnde houver ofensa, que eu leve o perdão;\nOnde houver discórdia, que eu leve a união;\nOnde houver dúvida, que eu leve a fé;\nOnde houver erro, que eu leve a verdade;\nOnde houver desespero, que eu leve a esperança;\nOnde houver tristeza, que eu leve a alegria;\nOnde houver trevas, que eu leve a luz.\n\nÓ Mestre, fazei que eu procure mais:\nConsolar, que ser consolado;\nCompreender, que ser compreendido;\nAmar, que ser amado.\nPois é dando que se recebe,\nÉ perdoando que se é perdoado,\nE é morrendo que se vive para a vida eterna. Amém.",
            latinText = "Domine, fac me instrumentum pacis tuae.\n\nUbi odium, ibi amorem seram;\nUbi iniuria, veniam;\nUbi discordia, unionem;\nUbi dubium, fidem;\nUbi error, veritatem;\nUbi desperatio, spem;\nUbi tristitia, laetitiam;\nUbi tenebrae, lucem.\n\nO Divine Magister, fac ut non quaeram tantum:\nConsolari, quantum consolare;\nIntellegi, quantum intellegere;\nDiligi, quantum diligere.\nQuia dando enim recipitur,\nIgnoscendo ignoscitur,\nEt moriendo orimur in vitam aeternam. Amen.",
            attribution = "Tradição Franciscana Seráfica",
            notes = "Oração universal pela reconciliação evangélica dos corações.",
            isFavorite = true
        ),
        SeraphicPrayer(
            id = 2,
            title = "Bênção de São Francisco a Frei Leão",
            latinTitle = "Benedictio Sancti Francisci ad Fratrem Leonem",
            category = "Louvor e Bênção",
            portugueseText = "O Senhor te abençoe e te guarde.\nO Senhor te mostre a Sua face e tenha misericórdia de ti.\nO Senhor volva para ti o Seu olhar e te dê a paz.\n\nO Senhor te abençoe, irmão(ã).\nEm nome do Pai, e do Filho, e do Espírito Santo. Amém.",
            latinText = "Benedicat tibi Dominus et custodiat te;\nOstendat Dominus faciem suam tibi et misereatur tui;\nConvertat Dominus vultum suum ad te et det tibi pacem.\n\nDominus benedicat te, frater.\nIn nomine Patris, et Filii, et Spiritus Sancti. Amen.",
            attribution = "São Francisco de Assis (Escrita no Monte Alverne)",
            notes = "Bênção bíblica (Números 6, 24-26) que São Francisco escreveu do próprio punho com o sinal do Tau para consolar Frei Leão.",
            isFavorite = true
        ),
        SeraphicPrayer(
            id = 3,
            title = "Cântico das Criaturas (Cântico do Irmão Sol)",
            latinTitle = "Canticum Fratris Solis",
            category = "Louvor e Bênção",
            portugueseText = "Altíssimo, onipotente, bom Senhor,\nteus são os louvores, a glória, a honra e toda a bênção.\nA ti só, Altíssimo, eles convêm,\ne homem algum é digno de te nomear.\n\nLouvado sejas, meu Senhor, com todas as tuas criaturas,\nespecialmente o senhor irmão Sol,\nque clareia o dia e por ele nos alumias.\nEle é belo e radiante com grande esplendor:\nde ti, Altíssimo, traz a imagem.\n\nLouvado sejas, meu Senhor, pela irmã Lua e pelas estrelas:\nno céu as formaste claras, preciosas e belas.\nLouvado sejas, meu Senhor, pelo irmão Vento,\npelo ar, nublado ou sereno, e todo o tempo,\npelo qual às tuas criaturas dás sustento.\n\nLouvado sejas, meu Senhor, pela irmã Água,\nque é muito útil e humilde, e preciosa e casta.\nLouvado sejas, meu Senhor, pelo irmão Fogo,\npelo qual iluminas a noite:\nele é belo e robusto e forte.\n\nLouvado sejas, meu Senhor, por nossa irmã a mãe Terra,\nque nos sustenta e governa,\ne produz diversos frutos com coloridas flores e ervas.\n\nLouvado sejas, meu Senhor, pelos que perdoam por teu amor\ne suportam enfermidades e tribulações.\nBem-aventurados os que as sustentam em paz,\npois por ti, Altíssimo, serão coroados.\n\nLouvado sejas, meu Senhor, por nossa irmã a Morte corporal,\nda qual homem algum pode escapar.\nLouvai e bendizei a meu Senhor,\ne dai-lhe graças e servi-o com grande humildade. Amém.",
            latinText = "Altissime, omnipotens, bone Domine,\ntuae sunt laudes, gloria, et honor et omnis benedictio.\nTibi soli, Altissime, conveniunt,\net nullus homo est dignus te nominare.\n\nLaudatus sis, Domine mi, cum omnibus creaturis tuis,\nspecialiter domino fratre Sole,\nqui est dies et illuminas nos per eum...\nLaudatus sis, mi Domine, per sororem nostram Mortem corporalem,\na qua nullus homo vivens potest evadere.\nLaudate et benedicite Dominum meum, et gratias agite illi et servite cum magna humilitate. Amen.",
            attribution = "São Francisco de Assis (Composto em São Damião)",
            notes = "Obra-prima mística e poética de Francisco, louvando ao Criador em todas as criaturas.",
            isFavorite = false
        ),
        SeraphicPrayer(
            id = 4,
            title = "Oração Diante do Crucifixo de São Damião",
            latinTitle = "Oratio ante Crucem Sancti Damiani",
            category = "Paz",
            portugueseText = "Sumo e glorioso Deus,\niluminai as trevas do meu coração.\n\nDai-me uma fé reta,\numa esperança certa\ne uma caridade perfeita;\nsensibilidade e conhecimento, Senhor,\npara que eu cumpra o vosso santo e verdadeiro mandamento.\nAmém.",
            latinText = "Summe et gloriose Deus,\nillumina tenebras cordis mei.\n\nEt da mihi fidem rectam,\nspem certam,\net caritatem perfectam;\nsensum et cognitionem, Domine,\nut faciam tuum sanctum et verace mandatum.\nAmen.",
            attribution = "São Francisco de Assis (Início de sua conversão)",
            notes = "Rezada pelo Santo na igrejinha em ruínas diante do milagroso Crucifixo pintado.",
            isFavorite = true
        ),
        SeraphicPrayer(
            id = 5,
            title = "Ladainha Seráfica de São Francisco",
            latinTitle = "Litaniae Sancti Francisci Assisiensis",
            category = "Ladainha",
            portugueseText = "Senhor, tende piedade de nós.\nCristo, tende piedade de nós.\nSenhor, tende piedade de nós.\nJesus Cristo, ouvi-nos.\nJesus Cristo, atendei-nos.\n\nPai celeste que sois Deus, tende piedade de nós.\nFilho Redentor do mundo que sois Deus, tende piedade de nós.\nEspírito Santo que sois Deus, tende piedade de nós.\nSantíssima Trindade que sois um só Deus, tende piedade de nós.\n\nSanta Maria, Rainha da Ordem Seráfica, rogai por nós.\nSão Francisco de Assis, Pai Seráfico, rogai por nós.\nSão Francisco, admirável imitador de Cristo, rogai por nós.\nSão Francisco, ardente amante da Cruz, rogai por nós.\nSão Francisco, marcado com as Chagas do Salvador, rogai por nós.\nSão Francisco, amante da Santa Pobreza, rogai por nós.\nSão Francisco, espelho de humildade e simplicidade, rogai por nós.\nSão Francisco, consolador dos aflitos, rogai por nós.\nSão Francisco, pregoeiro da paz e do bem, rogai por nós.\n\nCordeiro de Deus que tirais o pecado do mundo, perdoai-nos, Senhor.\nCordeiro de Deus que tirais o pecado do mundo, ouvi-nos, Senhor.\nCordeiro de Deus que tirais o pecado do mundo, tende piedade de nós.\n\nV. Rogai por nós, bem-aventurado Pai São Francisco.\nR. Para que sejamos dignos das promessas de Cristo. Amém.",
            latinText = "Kyrie, eleison.\nChriste, eleison.\nKyrie, eleison.\nChriste, audi nos.\nChriste, exaudi nos.\n\nPater de caelis Deus, miserere nobis.\nFili Redemptor mundi Deus, miserere nobis.\nSpiritus Sancte Deus, miserere nobis.\nSancta Trinitas unus Deus, miserere nobis.\n\nSancta Maria, Regina Ordinis Minorum, ora pro nobis.\nSancte Francisce Assisiensis, ora pro nobis.\nSancte Francisce, seraphico ardore succense, ora pro nobis.\nSancte Francisce, vulneribus Christi insignite, ora pro nobis...\nAgnus Dei, qui tollis peccata mundi, parce nobis, Domine. Amen.",
            attribution = "Liturgia Própria da Ordem dos Frades Menores",
            notes = "Invocação solene das virtudes seráficas do Santo de Assis.",
            isFavorite = false
        ),
        SeraphicPrayer(
            id = 6,
            title = "Oração do Espelho de Cristo (Santa Clara de Assis)",
            latinTitle = "Oratio Sanctae Clarae",
            category = "Maria Santíssima",
            portugueseText = "Olha para dentro desse Espelho todos os dias, ó serva de Cristo, e espelha nele continuamente a tua face, para que possas vestir-te toda por dentro e por fora de adornos de todas as virtudes.\n\nPois nesse Espelho resplandece a bendita pobreza, a santa humildade e a inefável caridade de Nosso Senhor Jesus Cristo.\nContempla no início deste Espelho a pobreza dAquele que foi posto no presépio e envolto em panos.\nNo meio do Espelho, considera a santa humildade e a fadiga que suportou pelos homens.\nNo fim deste mesmo Espelho, contempla a inefável caridade pela qual quis sofrer e morrer no lenho da Santa Cruz. Amém.",
            latinText = "Inspice cotidie speculum istud, et in eo faciem tuam indesinenter speculare... In quo speculo refulget beata paupertas, sancta humilitas et ineffabilis caritas. Amen.",
            attribution = "Santa Clara de Assis (Cartas a Santa Inês de Praga)",
            notes = "Instrução contemplativa sobre a conformação a Cristo Crucificado.",
            isFavorite = false
        ),
        SeraphicPrayer(
            id = 7,
            title = "Responso de Santo Antônio (Si Quaeris Miracula)",
            latinTitle = "Responsorium Sancti Antonii (Si Quaeris Miracula)",
            category = "Intercessão",
            portugueseText = "Se milagres desejais,\nRecorrei a Santo Antônio;\nVereis fugir o demônio\nE as tentações infernais.\n\nRecupera-se o perdido,\nRompe-se a dura prisão\nE no auge do furacão\nCede o mar embravecido.\n\nTodos os males humanos\nSe moderam, se retiram,\nDigam-no aqueles que o viram,\nDigam-no os paduanos.\n\nGlória ao Pai, e ao Filho, e ao Espírito Santo.\n\nV. Rogai por nós, bem-aventurado Antônio.\nR. Para que sejamos dignos das promessas de Cristo. Amém.",
            latinText = "Si quaeris miracula,\nMors, error, calamitas,\nDaemon, lepra fugiunt,\nAegri surgunt sani.\n\nCedunt mare, vincula,\nMembra resque perditas\nPetunt et accipiunt\nIuvenes et cani.\n\nPereunt pericula,\nCessat et necessitas,\nNarrent hi qui sentiunt,\nDicant Paduani.\n\nGloria Patri, et Filio, et Spiritui Sancto.\n\nV. Ora pro nobis, beate Antoni.\nR. Ut digni efficiamur promissionibus Christi. Amen.",
            attribution = "Frei Juliano de Espira, OFM (Século XIII)",
            notes = "Célebre hino de intercessão cantado tradicionalmente às terças-feiras.",
            isFavorite = true
        ),
        SeraphicPrayer(
            id = 8,
            title = "Oração do Angelus Domini",
            latinTitle = "Angelus Domini",
            category = "Maria Santíssima",
            portugueseText = "V. O Anjo do Senhor anunciou a Maria.\nR. E ela concebeu do Espírito Santo.\nAve Maria...\n\nV. Eis aqui a serva do Senhor.\nR. Faça-se em mim segundo a vossa palavra.\nAve Maria...\n\nV. E o Verbo se fez carne.\nR. E habitou entre nós.\nAve Maria...\n\nV. Rogai por nós, Santa Mãe de Deus.\nR. Para que sejamos dignos das promessas de Cristo.\n\nOremos: Infundi, Senhor, a vossa graça em nossas almas, para que nós, que pela anunciação do Anjo conhecemos a encarnação de Jesus Cristo, vosso Filho, pela sua Paixão e Cruz sejamos conduzidos à glória da ressurreição. Pelo mesmo Cristo, Nosso Senhor. Amém.",
            latinText = "V. Angelus Domini nuntiavit Mariae.\nR. Et concepit de Spiritu Sancto.\nAve Maria...\n\nV. Ecce ancilla Domini.\nR. Fiat mihi secundum verbum tuum.\nAve Maria...\n\nV. Et Verbum caro factum est.\nR. Et habitavit in nobis.\nAve Maria...\n\nV. Ora pro nobis, sancta Dei Genetrix.\nR. Ut digni efficiamur promissionibus Christi.\n\nOremus: Gratiam tuam, quaesumus, Domine, mentibus nostris infunde: ut qui, Angelo nuntiante, Christi Filii tui incarnationem cognovimus; per passionem eius et crucem, ad resurrectionis gloriam perducamur. Per eundem Christum Dominum nostrum. Amen.",
            attribution = "Tradição Eclesial e Franciscana (Promovida por São Boaventura no Capítulo de Pisa em 1263)",
            notes = "Rezada pontualmente às 6h, 12h e 18h ao toque dos sinos.",
            isFavorite = true
        )
    )

    fun getInitialLiturgicalDays(): List<LiturgicalDay1962> = listOf(
        LiturgicalDay1962(
            id = 1,
            dateCode = "10-04",
            dayTitle = "São Francisco de Assis, Confessor",
            latinDayTitle = "Sancti Francisci Assisiensis, Confessoris",
            season = "Tempo depois de Pentecostes",
            rankClass = "IIIª Classe (Iª Classe na Ordem Seráfica)",
            color = "Branco",
            introitPt = "Longe de mim gloriar-me, a não ser na cruz de nosso Senhor Jesus Cristo, pela qual o mundo está crucificado para mim, e eu para o mundo. (Sl 141) Com a minha voz clamei ao Senhor: com a minha voz supliquei ao Senhor. Glória ao Pai... Longe de mim gloriar-me...",
            introitLat = "Mihi autem absit gloriari, nisi in cruce Domini nostri Iesu Christi: per quem mihi mundus crucifixus est, et ego mundo. (Ps 141) Voce mea ad Dominum clamavi: voce mea ad Dominum deprecatus sum. Gloria Patri... Mihi autem absit gloriari...",
            collectPt = "Ó Deus, que pela vida e méritos do bem-aventurado Francisco dilatastes a vossa Igreja com o nascimento de uma nova família: concedei-nos que, a seu exemplo, desprezemos as coisas terrenas e nos alegremos na eterna participação dos dons celestes. Por Nosso Senhor Jesus Cristo.",
            collectLat = "Deus, qui Ecclesiam tuam, beati Francisci meritis foetura novae prolis amplificas: tribue nobis; ex eius imitatione, terrena despicere, et caelestium donorum semper participatione gaudere. Per Dominum nostrum Iesum Christum.",
            epistleRef = "Gálatas 6, 14-18",
            epistlePt = "Irmãos: Longe de mim gloriar-me a não ser na cruz de nosso Senhor Jesus Cristo, pela qual o mundo está crucificado para mim e eu para o mundo. Pois em Cristo Jesus de nada vale a circuncisão ou a incircuncisão, mas uma nova criatura. A todos os que seguirem esta regra, paz e misericórdia venham sobre eles e sobre o Israel de Deus. Doravante ninguém me moleste, pois trago em meu corpo as marcas de Jesus. A graça de nosso Senhor Jesus Cristo esteja com o vosso espírito, irmãos. Amém.",
            epistleLat = "Fratres: Mihi autem absit gloriari, nisi in cruce Domini nostri Iesu Christi: per quem mihi mundus crucifixus est, et ego mundo. In Christo enim Iesu neque circumcisio aliquid valet, neque praeputium, sed nova creatura. Et quicumque hanc regulam secuti fuerint, pax super illos, et misericordia, et super Israel Dei. De cetero nemo mihi molestus sit: ego enim stigmata Domini Iesu in corpore meo porto. Gratia Domini nostri Iesu Christi cum spiritu vestro, fratres. Amen.",
            gradualPt = "A boca do justo medita a sabedoria, e sua língua fala a justiça. A lei de seu Deus está em seu coração, e seus passos não vacilarão. Aleluia, aleluia. Este é Francisco, homem católico e todo apostólico.",
            gradualLat = "Os iusti meditabitur sapientiam, et lingua eius loquetur iudicium. Lex Dei eius in corde ipsius: et non supplantabuntur gressus eius. Alleluia, alleluia. Hic est Franciscus, pauper et humilis, caelum dives ingreditur, hymnis caelestibus honoratur.",
            gospelRef = "Mateus 11, 25-30",
            gospelPt = "Naquele tempo, Jesus tomou a palavra e disse: 'Eu te louvo, Pai, Senhor do céu e da terra, porque escondeste estas coisas aos sábios e prudentes, e as revelaste aos pequeninos. Sim, Pai, porque assim foi do teu agrado. Todas as coisas me foram entregues por meu Pai; e ninguém conhece o Filho senão o Pai; e ninguém conhece o Pai senão o Filho, e aquele a quem o Filho o quiser revelar. Vinde a mim, todos vós que estais cansados e carregados, e eu vos aliviarei. Tomai sobre vós o meu jugo e aprendei de mim, porque sou manso e humilde de coração, e achareis descanso para vossas almas. Porque o meu jugo é suave e o meu fardo é leve.'",
            gospelLat = "In illo tempore: Respondens Iesus, dixit: Confiteor tibi, Pater, Domine caeli et terrae, quia abscondisti haec a sapientibus, et prudentibus, et revelasti ea parvulis. Ita Pater: quoniam sic fuit placitum ante te. Omnia mihi tradita sunt a Patre meo. Et nemo novit Filium, nisi Pater: neque Patrem quis novit, nisi Filius, et cui voluerit Filius revelare. Venite ad me omnes qui laboratis, et onerati estis, et ego reficiam vos. Tollite iugum meum super vos, et discite a me, quia mitis sum, et humilis corde: et invenietis requiem animabus vestris. Iugum enim meum suave est, et onus meum leve.",
            offertoryPt = "Minha fidelidade e minha misericórdia estarão com ele; e em meu nome será exaltado o seu poder.",
            offertoryLat = "Veritas mea, et misericordia mea cum ipso: et in nomine meo exaltabitur cornu eius.",
            secretPt = "Santificai, Senhor, os dons oferecidos, e, pela intercessão do bem-aventurado Francisco, purificai-nos de todas as manchas de nossos pecados. Por Nosso Senhor Jesus Cristo.",
            secretLat = "Munera tibi, Domine, dicata sanctifica: et, intercedente beato Francisco, nos per haec a cunctis peccatorum nostrorum maculis emunda. Per Dominum nostrum.",
            communionPt = "Servo fiel e prudente, que o Senhor constituiu sobre sua família, para dar-lhe no devido tempo a medida do trigo.",
            communionLat = "Fidelis servus et prudens, quem constituit dominus super familiam suam: ut det illis in tempore tritici mensuram.",
            postCommunionPt = "Que a vossa graça celeste, Senhor, nós vos pedimos, santifique a nós que fomos alimentados por estes sagrados mistérios, pelos méritos do bem-aventurado Francisco. Por Nosso Senhor Jesus Cristo.",
            postCommunionLat = "Ecclesiam tuam, quaesumus Domine, gratia caelestis amplificet: quam beati Francisci Confessoris tui gloriosis meritis et exemplis illustrare voluisti. Per Dominum nostrum.",
            rubrics = "Missa Própria Seráfica. Glória dicitur. Credo non dicitur nisi in ecclesiis Ordinis. Prefácio Comum."
        ),
        LiturgicalDay1962(
            id = 2,
            dateCode = "09-14",
            dayTitle = "Exaltação da Santa Cruz",
            latinDayTitle = "In Exaltatione Sanctae Crucis",
            season = "Tempo depois de Pentecostes",
            rankClass = "IIª Classe",
            color = "Vermelho",
            introitPt = "Nós, porém, devemos gloriar-nos na Cruz de Nosso Senhor Jesus Cristo: na qual está a nossa salvação, vida e ressurreição: pela qual fomos salvos e livres. (Sl 66) Deus tenha piedade de nós e nos abençoe...",
            introitLat = "Nos autem gloriari oportet in Cruce Domini nostri Iesu Christi: in quo est salus, vita, et resurrectio nostra: per quem salvati, et liberati sumus. (Ps 66) Deus misereatur nostri, et benedicat nobis...",
            collectPt = "Ó Deus, que nos alegrais com a anual festividade da Exaltação da Santa Cruz: concedei-nos que, conhecendo nesta terra o seu mistério, alcancemos no céu os prêmios da sua redenção. Por Nosso Senhor Jesus Cristo.",
            collectLat = "Deus, qui nos hodierna die Exaltationis sanctae Crucis annua solemnitate laetificas: praesta, quaesumus; ut, cuius mysterium in terra cognovimus, eius redemptionis praemia in caelo mereamur. Per Dominum.",
            epistleRef = "Filipenses 2, 5-11",
            epistlePt = "Irmãos: Tende em vós o mesmo sentimento que houve em Cristo Jesus: o qual, tendo a natureza de Deus, não julgou rapina ser igual a Deus, mas humilhou-se a si mesmo, tomando a forma de servo, fazendo-se semelhante aos homens e obediente até a morte, e morte de cruz. Por isso Deus o exaltou soberanamente...",
            epistleLat = "Fratres: Hoc enim sentite in vobis, quod et in Christo Iesu: qui cum in forma Dei esset, non rapinam arbitratus est esse se aequalem Deo: sed semetipsum exinanivit, formam servi accipiens, in similitudinem hominum factus, et habitu inventus ut homo. Humiliavit semetipsum factus obediens usque ad mortem, mortem autem crucis...",
            gradualPt = "Cristo se fez por nós obediente até a morte, e morte de cruz. Por isso Deus o exaltou e lhe deu um nome que está acima de todo nome.",
            gradualLat = "Christus factus est pro nobis obediens usque ad mortem, mortem autem crucis. Propter quod et Deus exaltavit illum, et dedit illi nomen, quod est super omne nomen.",
            gospelRef = "João 12, 31-36",
            gospelPt = "Naquele tempo, disse Jesus às multidões dos judeus: 'Agora é o julgamento deste mundo; agora será expulso o príncipe deste mundo. E eu, quando for levantado da terra, atrairei todos a mim.' Ele dizia isto para indicar de que morte havia de morrer.",
            gospelLat = "In illo tempore: Dixit Iesus turbis Iudaeorum: Nunc iudicium est mundi: nunc princeps huius mundi eiicietur foras. Et ego si exaltatus fuero a terra, omnia traham ad meipsum. Hoc autem dicebat, significans qua morte esset moriturus.",
            offertoryPt = "Pelo sinal da Santa Cruz protegei o vosso povo, Senhor, contra as insídias de todos os inimigos.",
            offertoryLat = "Protege, Domine, plebem tuam per signum sanctae Crucis, ab omnibus insidiis inimicorum omnium.",
            secretPt = "Alimentados pelo Corpo e Sangue de Nosso Senhor Jesus Cristo, pelo qual a Cruz foi santificada, rogamo-vos, Senhor, que nos purifiqueis de todo pecado.",
            secretLat = "Iesu Christi Domini nostri Corpore et Sanguine saginandi, per quem Crucis est sanctificatum vexillum: quaesumus, Domine Deus noster; ut, sicut illud adorare meruimus, ita perennis eius gloriae salutaris potiamur effectu. Per eundem Dominum.",
            communionPt = "Pelo sinal da Cruz livrai-nos de nossos inimigos, ó nosso Deus.",
            communionLat = "Per signum Crucis de inimicis nostris libera nos, Deus noster.",
            postCommunionPt = "Assisti-nos, Senhor nosso Deus: e defendei com perpétua proteção aqueles a quem fazeis alegrar pela veneração da Santa Cruz. Por Nosso Senhor Jesus Cristo.",
            postCommunionLat = "Adesto nobis, Domine Deus noster: et, quos sanctae Crucis laetari facis honore, eius quoque perpetuis defende subsidiis. Per Dominum nostrum.",
            rubrics = "Glória dicitur. Credo dicitur. Prefácio da Santa Cruz."
        ),
        LiturgicalDay1962(
            id = 3,
            dateCode = "09-11",
            dayTitle = "Feria Sexta infra Hebdomadam XIV post Pentecosten",
            latinDayTitle = "Feria Sexta infra Hebdomadam XIV post Pentecosten",
            season = "Tempo depois de Pentecostes",
            rankClass = "IVª Classe (Comemoração dos Santos Proto e Jacinto)",
            color = "Verde",
            introitPt = "Olhai, ó Deus, nosso protetor, e contemplai a face de vosso Cristo: porque vale mais um dia em vossos átrios do que milhares noutro lugar. (Sl 83) Quão amáveis são os vossos tabernáculos, Senhor dos Exércitos! A minha alma suspira e desfalece pelos átrios do Senhor. Glória ao Pai...",
            introitLat = "Protector noster, aspice, Deus, et respice in faciem Christi tui: quia melior est dies una in atriis tuis super millia. (Ps 83) Quam dilecta tabernacula tua, Domine virtutum! concupiscit, et deficit anima mea in atria Domini. Gloria Patri...",
            collectPt = "Guardai, Senhor, a vossa Igreja com perpétua propiciação: e porque sem vós a mortalidade humana vacila, seja pelos vossos auxílios afastada dos danos e dirigida para a salvação. Por Nosso Senhor Jesus Cristo.",
            collectLat = "Custodi, Domine, quaesumus, Ecclesiam tuam propitiatione perpetua: et quia sine te labitur humana mortalitas; tuis semper auxiliis et abstrahatur a noxiis, et ad salutaria dirigatur. Per Dominum nostrum Iesum Christum.",
            epistleRef = "Gálatas 5, 16-24",
            epistlePt = "Irmãos: Digo-vos: Andai no Espírito, e não realizareis os desejos da carne. Pois a carne cobiça contra o espírito, e o espírito contra a carne; estes são opostos entre si, para que não façais tudo o que quereis. Mas, se sois guiados pelo Espírito, não estais debaixo da lei. Ora, as obras da carne são manifestas: fornicação, impureza, idolatria, inimizades, discórdias, ciúmes, iras, dissensões... Pelo contrário, o fruto do Espírito é: caridade, alegria, paz, paciência, benignidade, bondade, longanimidade, brandura, fé, modéstia, continência, castidade.",
            epistleLat = "Fratres: Dico autem in Christo: Spiritu ambulate, et desideria carnis non perficietis. Caro enim concupiscit adversus spiritum, spiritus autem adversus carnem: haec enim sibi invicem adversantur, ut non quaecumque vultis, illa faciatis. Quod si Spiritu ducimini, non estis sub lege. Manifesta sunt autem opera carnis, quae sunt fornicatio, immunditia... Fructus autem Spiritus est: caritas, gaudium, pax, patientia, benignitas, bonitas, longanimitas, mansuetudo, fides, modestia, continentia, castitas.",
            gradualPt = "Mais vale confiar no Senhor do que no homem. Mais vale esperar no Senhor do que confiar nos príncipes. Aleluia, aleluia. Vinde, exultemos no Senhor, cantemos com júbilo a Deus, nosso Salvador.",
            gradualLat = "Bonum est confidere in Domino, quam confidere in homine. Bonum est sperare in Domino, quam sperare in principibus. Alleluia, alleluia. Venite, exsultemus Domino, iubilemus Deo, salutari nostro.",
            gospelRef = "Mateus 6, 24-33",
            gospelPt = "Naquele tempo, disse Jesus aos seus discípulos: 'Ninguém pode servir a dois senhores; porque ou há de odiar a um e amar o outro, ou se apegará a um e desprezará o outro. Não podeis servir a Deus e ao dinheiro. Portanto, vos digo: não vos preocupeis com a vossa vida pelo que haveis de comer, nem com o vosso corpo pelo que haveis de vestir... Olhai as aves do céu: não semeiam, não colhem... Buscai primeiro o Reino de Deus e a sua justiça, e todas estas coisas vos serão dadas por acréscimo.'",
            gospelLat = "In illo tempore: Dixit Iesus discipulis suis: Nemo potest duobus dominis servire: aut enim unum odio habebit, et alterum diliget: aut unum sustinebit, et alterum contemnet. Non potestis Deo servire et mammonae. Ideo dico vobis, ne solliciti sitis animae vestrae quid manducetis, neque corpori vestro quid induamini... Quaerite primum regnum Dei, et iustitiam eius: et haec omnia adiicientur vobis.",
            offertoryPt = "O anjo do Senhor acampa ao redor dos que o temem e os liberta: provai e vede como o Senhor é suave.",
            offertoryLat = "Immittet Angelus Domini in circuitu timentium eum, et eripiet eos: gustate et videte, quoniam suavis est Dominus.",
            secretPt = "Concedei-nos, Senhor, nós vos suplicamos, que esta hóstia salutar seja a purificação de nossos delitos e a propiciação de vossa majestade. Por Nosso Senhor Jesus Cristo.",
            secretLat = "Concede nobis, Domine, quaesumus, ut haec hostia salutaris et nostrorum fiat expiatio delictorum, et tuae propitiatio maiestatis. Per Dominum.",
            communionPt = "Buscai primeiro o Reino de Deus, e todas as coisas vos serão dadas por acréscimo, diz o Senhor.",
            communionLat = "Primum quaerite regnum Dei, et omnia adiicientur vobis, dicit Dominus.",
            postCommunionPt = "Purifiquem-nos sempre, Senhor, os vossos sacramentos, e nos defendam: e nos conduzam à plenitude da eterna salvação. Por Nosso Senhor Jesus Cristo.",
            postCommunionLat = "Purificent semper et muniant tua sacramenta nos, Deus: et ad perpetuae ducant salvationis effectum. Per Dominum nostrum.",
            rubrics = "Missa da féria corrente. Glória non dicitur. Credo non dicitur. Prefácio Comum."
        ),
        LiturgicalDay1962(
            id = 4,
            dateCode = "12-08",
            dayTitle = "Imaculada Conceição da Bem-Aventurada Virgem Maria",
            latinDayTitle = "In Conceptione Immaculata Beatae Mariae Virginis",
            season = "Advento",
            rankClass = "Iª Classe",
            color = "Branco",
            introitPt = "Alegro-me grandemente no Senhor, e minha alma se rejubila em meu Deus: porque me vestiu com as vestes da salvação e me cobriu com o manto da justiça, como uma noiva adornada com suas jóias. (Sl 29) Exaltar-te-ei, Senhor, porque me acolheste...",
            introitLat = "Gaudens gaudebo in Domino, et exsultabit anima mea in Deo meo: quia induit me vestimentis salutis, et indumento iustitiae circumdedit me, quasi sponsam ornatam monilibus suis. (Ps 29) Exaltabo te, Domine, quoniam suscepisti me...",
            collectPt = "Ó Deus, que pela Imaculada Conceição da Virgem preparastes para o vosso Filho digna morada: nós vos rogamos que, assim como a preservastes de toda mancha pela previsão da morte do mesmo vosso Filho, assim também nos concedais chegar purificados à vossa presença. Pelo mesmo Cristo Nosso Senhor.",
            collectLat = "Deus, qui per immaculatam Virginis Conceptionem dignum Filio tuo habitaculum praeparasti: quaesumus; ut qui ex morte eiusdem Filii tui praevisa, eam ab omni labe praeservasti, nos quoque mundos eius intercessione ad te pervenire concedas. Per eundem Dominum.",
            epistleRef = "Provérbios 8, 22-35",
            epistlePt = "O Senhor me possuiu no princípio de seus caminhos, desde o começo, antes que fizesse coisa alguma. Desde a eternidade fui ungida, e desde os tempos antigos, antes que a terra fosse criada. As profundezas ainda não existiam, e eu já tinha sido concebida...",
            epistleLat = "Dominus possedit me in initio viarum suarum, antequam quidquam faceret a principio. Ab aeterno ordinata sum, et ex antiquis, antequam terra fieret. Nondum erant abyssi, et ego iam concepta eram...",
            gradualPt = "Bendita és tu, Virgem Maria, pelo Senhor Deus altíssimo, mais do que todas as mulheres sobre a terra. Tu és a glória de Jerusalém, tu a alegria de Israel, tu a honra do nosso povo.",
            gradualLat = "Benedicta es tu, Virgo Maria, a Domino Deo excelso, prae omnibus mulieribus super terram. Tu gloria Ierusalem, tu laetitia Israel, tu honorificentia populi nostri.",
            gospelRef = "Lucas 1, 26-28",
            gospelPt = "Naquele tempo, foi enviado o anjo Gabriel por Deus a uma cidade da Galiléia, chamada Nazaré, a uma virgem desposada com um homem chamado José, da casa de Davi, e o nome da virgem era Maria. Entrando o anjo onde ela estava, disse: 'Ave, cheia de graça, o Senhor é contigo: bendita és tu entre as mulheres.'",
            gospelLat = "In illo tempore: Missus est Angelus Gabriel a Deo in civitatem Galilaeae, cui nomen Nazareth, ad Virginem desponsatam viro, cui nomen erat Ioseph, de domo David, et nomen Virginis Maria. Et ingressus Angelus ad eam dixit: Ave gratia plena: Dominus tecum: benedicta tu in mulieribus.",
            offertoryPt = "Ave, Maria, cheia de graça, o Senhor é contigo: bendita és tu entre as mulheres. Aleluia.",
            offertoryLat = "Ave Maria, gratia plena; Dominus tecum: benedicta tu in mulieribus. Alleluia.",
            secretPt = "Recebei, Senhor, a hóstia salutar que vos oferecemos na solenidade da Imaculada Conceição da Bem-Aventurada Virgem Maria: e concedei que, ao professar que pela vossa graça ela foi isenta de toda nódoa de pecado, sejamos livres de todas as nossas culpas. Por Nosso Senhor Jesus Cristo.",
            secretLat = "Salutarem hostiam, quam in Conceptione immaculata beatae Virginis Mariae tibi, Domine, offerimus, suscipe et praesta: ut, sicut illam tua gratia praeveniente ab omni labe profitetur immunem; ita nos, eius intercessione, a cunctis reatibus liberemur. Per Dominum.",
            communionPt = "Gloriosas coisas foram ditas de ti, Maria: porque fez em ti grandes maravilhas Aquele que é poderoso.",
            communionLat = "Gloriosa dicta sunt de te, Maria: quia fecit tibi magna qui potens est.",
            postCommunionPt = "Os sacramentos que recebemos, Senhor nosso Deus, reparem em nós as feridas daquele pecado do qual preservastes de modo singular a Imaculada Conceição da Bem-Aventurada Maria. Por Nosso Senhor Jesus Cristo.",
            postCommunionLat = "Sacramenta quae sumpsimus, Domine Deus noster: illius in nobis culpae vulnera sanent; a qua immaculatam beatae Mariae Conceptionem singulariter praeservasti. Per Dominum nostrum.",
            rubrics = "Festa Principal. Glória dicitur. Credo dicitur. Prefácio da Bem-Aventurada Virgem Maria (Et te in Conceptione Immaculata)."
        )
    )

    fun getInitialReminders(): List<ReminderSetting> = listOf(
        ReminderSetting(
            id = "angelus_6",
            title = "Angelus da Manhã",
            subtitle = "Ao despertar na aurora com a Santíssima Virgem",
            hour = 6,
            minute = 0,
            isEnabled = true,
            type = "ANGELUS"
        ),
        ReminderSetting(
            id = "angelus_12",
            title = "Angelus do Meio-Dia",
            subtitle = "Pausa meridiana contemplativa no mistério da Encarnação",
            hour = 12,
            minute = 0,
            isEnabled = true,
            type = "ANGELUS"
        ),
        ReminderSetting(
            id = "angelus_18",
            title = "Angelus da Tarde (Ave Maria)",
            subtitle = "Ao pôr do sol, agradecimento pelas graças do dia",
            hour = 18,
            minute = 0,
            isEnabled = true,
            type = "ANGELUS"
        ),
        ReminderSetting(
            id = "hora_laudes",
            title = "Laudes Seráficas",
            subtitle = "Hora Canônica matutina de louvor com a Criação",
            hour = 6,
            minute = 30,
            isEnabled = false,
            type = "CANONICAL_HOUR"
        ),
        ReminderSetting(
            id = "hora_terca",
            title = "Hora Terça (9h)",
            subtitle = "Hora Canônica evocando a descida do Espírito Santo",
            hour = 9,
            minute = 0,
            isEnabled = false,
            type = "CANONICAL_HOUR"
        ),
        ReminderSetting(
            id = "hora_sexta",
            title = "Hora Sexta (12h)",
            subtitle = "Hora Canônica evocando a crucifixão de Nosso Senhor",
            hour = 12,
            minute = 15,
            isEnabled = false,
            type = "CANONICAL_HOUR"
        ),
        ReminderSetting(
            id = "hora_noa",
            title = "Hora Noa (15h)",
            subtitle = "Hora da Misericórdia e morte redentora de Jesus",
            hour = 15,
            minute = 0,
            isEnabled = true,
            type = "CANONICAL_HOUR"
        ),
        ReminderSetting(
            id = "hora_vesperas",
            title = "Vésperas Franciscanas",
            subtitle = "Oração do entardecer com o Cântico do Magnificat",
            hour = 18,
            minute = 30,
            isEnabled = true,
            type = "CANONICAL_HOUR"
        ),
        ReminderSetting(
            id = "hora_completas",
            title = "Completas Seráficas",
            subtitle = "Oração da noite antes do repouso noturno",
            hour = 21,
            minute = 0,
            isEnabled = true,
            type = "CANONICAL_HOUR"
        ),
        ReminderSetting(
            id = "santo_terco",
            title = "Santo Terço & Coroa Franciscana",
            subtitle = "Momento devocional do Rosário e Sete Alegrias",
            hour = 20,
            minute = 0,
            isEnabled = true,
            type = "ROSARY"
        )
    )

    fun getInitialAudioPackages(): List<AudioPackage> = listOf(
        AudioPackage(
            id = "pkg_core",
            title = "Liturgia & Santos (Modo Offline Básico)",
            description = "Textos litúrgicos completos, síntese de áudio para latim e português e ambiências sacras integradas.",
            sizeMb = 14,
            isDownloaded = true,
            downloadProgress = 1.0f,
            isDownloading = false
        ),
        AudioPackage(
            id = "pkg_coroa",
            title = "Coroa Franciscana das Sete Alegrias HD",
            description = "Gravações solenes com contemplações estendidas das 7 alegrias marianas e jaculatórias seráficas.",
            sizeMb = 26,
            isDownloaded = false,
            downloadProgress = 0f,
            isDownloading = false
        ),
        AudioPackage(
            id = "pkg_gregoriano",
            title = "Antifonário Gregoriano & Órgão Litúrgico",
            description = "Seleção harmônica expandida de cantos tradicionais (Kyrie, Salve Regina Seraphica e Te Deum).",
            sizeMb = 38,
            isDownloaded = false,
            downloadProgress = 0f,
            isDownloading = false
        ),
        AudioPackage(
            id = "pkg_missal_audio",
            title = "Pronúncia Latina Tradicional do Missal 1962",
            description = "Guias orais fonéticos da pronúncia eclesiástica e romana para sacerdotes e fiéis.",
            sizeMb = 21,
            isDownloaded = false,
            downloadProgress = 0f,
            isDownloading = false
        )
    )
}

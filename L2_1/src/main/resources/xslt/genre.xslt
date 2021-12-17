<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="3.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" indent="yes" media-type="text/html" encoding="UTF-8"/>

    <xsl:template match="/">

        <html>
            <head>
                <title>Genre XSTL</title>
                <style type="text/css">
                    body {
                    margin: 0;
                    }

                    .header {
                    margin-left: -10px;
                    padding-top: 2px;
                    top: 0;
                    left: 0;
                    }

                    .header img {
                    height: 100px;
                    float: left;
                    }

                    .header h1 {
                    text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
                    color: #f28f47;
                    }

                    .header hr {
                    margin-top: 10px;
                    height: 1px;
                    }

                    .main {
                    margin-top: 10px;
                    }

                    .main .filter-block {
                    color: #8f26e2;
                    font-size: 20px;
                    margin-left: 5%;
                    }

                    .main .filter-block .filter-line {
                    width: 20%;
                    display: inline-block;
                    }

                    .main .filter-block .filter-line input {
                    border: 1px solid red;
                    }


                    .table-book {
                    overflow-y: auto;
                    font-size: 20px;
                    margin: 5% 10% 1% 10%;
                    border: 1px solid rgb(242, 143, 71);
                    }

                    .table-book button {
                    margin-top: 11px;
                    background-color: white;
                    border: none;
                    }

                    .table-book .row {
                    display: flex;
                    justify-content: space-around;
                    }

                    .table-book .row .element {
                    width: 15rem;
                    line-height: 44px;
                    text-align: center;
                    }

                    .table-book .row .filler {
                    width: 13rem;
                    }

                    .table-book .head {
                    color: #fd0505;
                    background-color: rgba(242, 143, 71, 0.31);
                    }

                    .table-book .row .edit-modal-button {
                    text-decoration: none;
                    cursor: pointer;
                    text-align: center;
                    width: 2rem;
                    margin-top: 3px;
                    }

                    .table-book .row .del-button {
                    width: 2rem;
                    }
                </style>
            </head>
        </html>

    </xsl:template>

    <xsl:template match="ArrayList">

        <h1>Список жанров</h1>

        <div class="table-book">

            <div class="head row">
                <div class="element">Название</div>
            </div>

            <xsl:for-each select="item">

                <div class="element"><xsl:value-of select="name"/></div>

            </xsl:for-each>

        </div>


    </xsl:template>


</xsl:stylesheet>